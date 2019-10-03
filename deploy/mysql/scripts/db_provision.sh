sh /entrypoint.sh mysqld &
MYSQL_PID=$!
echo "MySQL PID: ${MYSQL_PID}"

DOCKER_IP=$(ip addr show eth0 | grep -E '^\s*inet' | grep -m1 global | awk '{ print $2 }' | sed 's|/.*||')
MYSQL_USER='root'
MYSQL_ROOT_PWD='root'
SCHEMA_NAME="vehicle_demo"

wait_mysql(){

	echo "MySQL - Waiting for port 3306 available..."
	# Wait for MySQL up & database ready.
	# TODO: Implement timeout for this loop.
	mysql -u $MYSQL_USER -p$MYSQL_ROOT_PWD --host=$DOCKER_IP -e "use mysql" --connect-timeout=5
	IS_MYSQL_UP=$?
	ATTEMPTS=0
	while [ $IS_MYSQL_UP -eq 1 ] && [ $ATTEMPTS -lt 20 ]
	do
		# Not started yet.
		echo "MySQL - Not started yet... Check Status: $IS_MYSQL_UP | Attempts: $ATTEMPTS"
		sleep 2
		mysql -u root -p$MYSQL_ROOT_PWD --host=$DOCKER_IP -e "use mysql" --connect-timeout=5
		IS_MYSQL_UP=$?
		((ATTEMPTS++))
	done

	echo "MySQL - Started! Check Status: $IS_MYSQL_UP | Attempts: $ATTEMPTS"

	if [ $IS_MYSQL_UP -eq 1 ]; then
		echo "MySQL - Didn't Start. Exiting! Check Status: $IS_MYSQL_UP | Attempts: $ATTEMPTS"
		exit 1
	fi
}

create_database(){

	echo "MySQL - Creating database '$SCHEMA_NAME'"
	ATTEMPTS=0
	IS_COMMAND_OK=1
	while [ $IS_COMMAND_OK -eq 1 ] && [ $ATTEMPTS -lt 20 ]
	do
		sleep 2
		mysql -u root -p$MYSQL_ROOT_PWD --host=$DOCKER_IP --execute="create database IF NOT EXISTS $SCHEMA_NAME;"
		IS_COMMAND_OK=$?
		((ATTEMPTS++))
	done

	if [ $IS_COMMAND_OK -eq 1 ]; then
		echo "MySQL - Cannot create the database $SCHEMA_NAME. Exiting!"
		exit 1
	fi
}


grant_access(){

	echo "Grant access to user $MYSQL_USER"

	MYSQL_GRANT_IPS="%"
	MYSQL_GRANT_QUERY="GRANT ALL PRIVILEGES ON $SCHEMA_NAME.* TO '$MYSQL_USER'@'$MYSQL_GRANT_IPS' IDENTIFIED BY '$MYSQL_USER' WITH GRANT OPTION;"
	echo "MySQL - Grant access for user '$MYSQL_USER' into database '$SCHEMA_NAME' for the following IP mask '$MYSQL_GRANT_IPS' using password '$MYSQL_USER' "
	mysql -u root -p$MYSQL_ROOT_PWD --host=$DOCKER_IP --execute="$MYSQL_GRANT_QUERY"
	IS_COMMAND_OK=$?
	if [ $IS_COMMAND_OK == 1 ]; then
		echo "MySQL - Cannot grant privileges. Exiting!"
		exit 1
	fi
}

drop_database(){

	echo "MySQL - Dropping database '$SCHEMA_NAME'"
	ATTEMPTS=0
	IS_COMMAND_OK=1
	while [ $IS_COMMAND_OK -eq 1 ] && [ $ATTEMPTS -lt 20 ]
	do
		sleep 2
		mysql -u root -p$MYSQL_ROOT_PWD --host=$DOCKER_IP --execute="drop database IF EXISTS $SCHEMA_NAME;"
		IS_COMMAND_OK=$?
		((ATTEMPTS++))
	done

	if [ $IS_COMMAND_OK -eq 1 ]; then
		echo "MySQL - Cannot drop the database $SCHEMA_NAME. Exiting!"
		exit 1
	fi
}

main(){

	if [[ $DROP_DATABASE -eq 0 ]]; then
		echo "Starting existing DB and services"
		wait_mysql
		create_database #IF NOT EXISTS
		grant_access
	else
		echo "Recreating DB"
		drop_database
		wait_mysql
		create_database
		grant_access
	fi

	echo "MySQL - Installation & configuration finished successfully"
	#wait on all background processes (mysql that was started on top)
	echo "Waiting for MySQL to exit"
	wait $MYSQL_PID

}

main
