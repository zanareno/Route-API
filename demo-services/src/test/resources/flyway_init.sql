DROP ALL OBJECTS;

CREATE TABLE `brand` (
  name VARCHAR(50) PRIMARY KEY
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `model` (
  id INT(3) AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  YEAR INT(4) NOT NULL,
  brand_name VARCHAR(50),
  FOREIGN KEY (brand_name) REFERENCES brand (name) ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `vehicle` (
  license_plate VARCHAR(10) PRIMARY KEY,
  avg_consumption DOUBLE(3,1) NOT NULL,
  fuel VARCHAR(10) NOT NULL,
  brand_name VARCHAR(50),
  model_id INT(3),
  FOREIGN KEY (brand_name) REFERENCES brand (name) ON UPDATE RESTRICT,
  FOREIGN KEY (model_id) REFERENCES model (id) ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `brand`(name) VALUES ('Ford'), ('Chrysler'), ('Citroën'), ('Hillman'), ('Chevrolet'), ('Cadillac'), ('BMW'), ('Austin'), ('Fillmore'), ('Fairthorpe'), ('Studebaker'),
                               ('Pontiac'), ('Buick'), ('Rambler'), ('Plymouth'), ('Volkswagen'), ('Jensen'), ('Oldsmobile'), ('Mercury'), ('Shelby'), ('Dodge'), ('Porsche'),
                               ('Toyota'), ('Mercedes-Benz'), ('MG'), ('Nissan'), ('Honda'), ('Renault'), ('Mazda'), ('Mitsubishi'), ('Subaru'), ('Lotus'), ('Lincoln'),
                               ('Maserati'), ('Saab'), ('Audi'), ('Suzuki');

INSERT INTO `model`(YEAR, brand_name, name) VALUES (1909 , 'Ford' , 'Model T'), (1926 , 'Chrysler' , 'Imperial'), (1948 , 'Citroën' , '2CV'), (1950 , 'Hillman' , 'Minx Magnificent'),
                                                 (1953 , 'Chevrolet' , 'Corvette'), (1954 , 'Chevrolet' , 'Corvette'), (1954 , 'Cadillac' , 'Fleetwood'),
                                                 (1955 , 'Chevrolet' , 'Corvette'), (1955 , 'Ford' , 'Thunderbird'), (1956 , 'Chevrolet' , 'Corvette'),
                                                 (1957 , 'Chevrolet' , 'Corvette'), (1957 , 'BMW' , '600'), (1958 , 'Chevrolet' , 'Corvette'), (1958 , 'BMW' , '600'),
                                                 (1958 , 'Ford' , 'Thunderbird'), (1959 , 'Austin' , 'Mini'), (1959 , 'Chevrolet' , 'Corvette'), (1959 , 'BMW' , '600'),
                                                 (1960 , 'Chevrolet' , 'Corvair'), (1960 , 'Chevrolet' , 'Corvette'), (1960 , 'Fillmore' , 'Fillmore'),
                                                 (1960 , 'Fairthorpe' , 'Rockette'), (1961 , 'Austin' , 'Mini Cooper'), (1961 , 'Studebaker' , 'Avanti'),
                                                 (1961 , 'Pontiac' , 'Tempest'), (1961 , 'Chevrolet' , 'Corvette'), (1962 , 'Pontiac' , 'Grand Prix'),
                                                 (1962 , 'Chevrolet' , 'Corvette'), (1962 , 'Studebaker' , 'Avanti'), (1962 , 'Buick' , 'Special'), (1963 , 'Austin' , 'Mini'),
                                                 (1963 , 'Austin' , 'Mini Cooper S'), (1963 , 'Rambler' , 'Classic'), (1963 , 'Ford' , 'E-Series'), (1963 , 'Studebaker' , 'Avanti')
                                                 , (1963 , 'Pontiac' , 'Grand Prix'), (1963 , 'Chevrolet' , 'Corvair 500'), (1963 , 'Chevrolet' , 'Corvette'),
                                                 (1964 , 'Chevrolet' , 'Corvette'), (1964 , 'Ford' , 'Mustang'), (1964 , 'Ford' , 'Galaxie'), (1964 , 'Pontiac' , 'GTO'),
                                                 (1964 , 'Pontiac' , 'LeMans'), (1964 , 'Pontiac' , 'Bonneville'), (1964 , 'Pontiac' , 'Grand Prix'), (1964 , 'Plymouth' , 'Fury'),
                                                 (1964 , 'Studebaker' , 'Avanti'), (1964 , 'Austin' , 'Mini Cooper'), (1965 , 'Ford' , 'Fairlane'), (1965 , 'Ford' , 'Mustang'),
                                                 (1965 , 'Ford' , 'Thunderbird'), (1965 , 'Pontiac' , 'GTO'), (1965 , 'Pontiac' , 'Grand Prix'), (1965 , 'Pontiac' , 'LeMans'),
                                                 (1965 , 'Pontiac' , 'Bonneville'), (1965 , 'Pontiac' , 'Tempest'), (1965 , 'Volkswagen' , 'Beetle'),
                                                 (1965 , 'Chevrolet' , 'Corvette'), (1966 , 'Ford' , 'Galaxie'), (1966 , 'Ford' , 'Mustang'), (1966 , 'Ford' , 'Falcon'),
                                                 (1966 , 'Ford' , 'Fairlane'), (1966 , 'Jensen' , 'Interceptor'), (1966 , 'Pontiac' , 'Bonneville'),
                                                 (1966 , 'Pontiac' , 'Grand Prix'), (1966 , 'Pontiac' , 'GTO'), (1966 , 'Pontiac' , 'LeMans'), (1966 , 'Pontiac' , 'Tempest'),
                                                 (1966 , 'Chevrolet' , 'Corvette'), (1966 , 'Oldsmobile' , 'Toronado'), (1967 , 'Volkswagen' , 'Beetle'),
                                                 (1967 , 'Pontiac' , 'Tempest'), (1967 , 'Pontiac' , 'Firebird'), (1967 , 'Pontiac' , 'Grand Prix'), (1967 , 'Pontiac' , 'GTO'),
                                                 (1967 , 'Pontiac' , 'LeMans'), (1967 , 'Pontiac' , 'Bonneville'), (1967 , 'Chevrolet' , 'Camaro'), (1967 , 'Chevrolet' , 'Bel Air')
                                                 , (1967 , 'Chevrolet' , 'Corvette'), (1967 , 'Ford' , 'Country'), (1967 , 'Ford' , 'Falcon'), (1967 , 'Ford' , 'Mustang'),
                                                 (1967 , 'Ford' , 'Thunderbird'), (1967 , 'Ford' , 'Fairlane'), (1967 , 'Mercury' , 'Cougar'), (1967 , 'Jensen' , 'Interceptor'),
                                                 (1968 , 'Pontiac' , 'Firebird'), (1968 , 'Pontiac' , 'Lemans'), (1968 , 'Pontiac' , 'GTO'), (1968 , 'Pontiac' , 'Bonneville'),
                                                 (1968 , 'Pontiac' , 'Grand Prix'), (1968 , 'Shelby' , 'GT500'), (1968 , 'Dodge' , 'Charger'), (1968 , 'Mercury' , 'Cougar'),
                                                 (1968 , 'Ford' , 'Mustang'), (1968 , 'Chevrolet' , 'Camaro'), (1968 , 'Chevrolet' , 'Corvette'), (1969 , 'Pontiac' , 'Firebird'),
                                                 (1969 , 'Pontiac' , 'Grand Prix'), (1969 , 'Pontiac' , 'GTO'), (1969 , 'Mercury' , 'Cougar'), (1969 , 'Chevrolet' , 'Camaro'),
                                                 (1969 , 'Chevrolet' , 'Corvette'), (1969 , 'Dodge' , 'Charger'), (1969 , 'Shelby' , 'GT350'), (1969 , 'Ford' , 'Mustang'),
                                                 (1969 , 'Plymouth' , 'Roadrunner'), (1970 , 'Chevrolet' , 'Camaro'), (1970 , 'Chevrolet' , 'Corvette'), (1970 , 'Pontiac' , 'GTO'),
                                                 (1970 , 'Pontiac' , 'Grand Prix'), (1970 , 'Mercury' , 'Cougar'), (1970 , 'Ford' , 'Mustang'), (1970 , 'Ford' , 'Torino'),
                                                 (1970 , 'Porsche' , '914'), (1970 , 'Dodge' , 'Charger'), (1971 , 'Ford' , 'Mustang'), (1971 , 'Chevrolet' , 'Vega'),
                                                 (1971 , 'Chevrolet' , 'Camaro'), (1971 , 'Pontiac' , 'GTO'), (1971 , 'Pontiac' , 'Grand Prix'), (1972 , 'Chevrolet' , 'Corvette'),
                                                 (1972 , 'Chevrolet' , 'Camaro'), (1972 , 'Citroën' , 'SM'), (1972 , 'Pontiac' , 'GTO'), (1972 , 'Pontiac' , 'Grand Prix'),
                                                 (1972 , 'Ford' , 'Thunderbird'), (1972 , 'Ford' , 'Mustang'), (1973 , 'Pontiac' , 'Grand Prix'), (1973 , 'Pontiac' , 'GTO'),
                                                 (1973 , 'Ford' , 'Mustang'), (1973 , 'Chevrolet' , 'Camaro'), (1973 , 'Chevrolet' , 'Monte Carlo'),
                                                 (1973 , 'Chevrolet' , 'Corvette'), (1974 , 'Chevrolet' , 'Camaro'), (1974 , 'Ford' , 'Mustang'), (1974 , 'Citroën' , 'CX'),
                                                 (1974 , 'Pontiac' , 'GTO'), (1974 , 'Pontiac' , 'Grand Prix'), (1975 , 'Chevrolet' , 'Camaro'), (1975 , 'Chevrolet' , 'Corvette'),
                                                 (1975 , 'Chevrolet' , 'Monza'), (1975 , 'Pontiac' , 'Grand Prix'), (1976 , 'Volkswagen' , 'Golf'),
                                                 (1976 , 'Pontiac' , 'Grand Prix'), (1976 , 'Chevrolet' , 'Camaro'), (1976 , 'Toyota' , 'Celica'), (1976 , 'Plymouth' , 'Volare'),
                                                 (1976 , 'Dodge' , 'Aspen'), (1977 , 'Chevrolet' , 'Camaro'), (1977 , 'Chevrolet' , 'Caprice'), (1977 , 'Mercedes-Benz' , 'W123'),
                                                 (1977 , 'MG' , 'MGB'), (1977 , 'Pontiac' , 'Grand Prix'), (1977 , 'Ford' , 'Thunderbird'), (1978 , 'Chevrolet' , 'Corvette'),
                                                 (1978 , 'Chevrolet' , 'Camaro'), (1978 , 'Toyota' , 'Celica'), (1978 , 'Plymouth' , 'Horizon'), (1978 , 'Dodge' , 'Omni'),
                                                 (1978 , 'Pontiac' , 'Grand Prix'), (1979 , 'Nissan' , '280ZX'), (1979 , 'Chevrolet' , 'LUV'), (1979 , 'Chevrolet' , 'Camaro'),
                                                 (1979 , 'Ford' , 'Mustang'), (1979 , 'Buick' , 'Riviera'), (1979 , 'Pontiac' , 'Grand Prix'), (1980 , 'Ford' , 'Mustang'),
                                                 (1980 , 'Ford' , 'Thunderbird'), (1980 , 'Chevrolet' , 'Citation'), (1980 , 'Chevrolet' , 'Camaro'), (1980 , 'Honda' , 'Civic'),
                                                 (1980 , 'Pontiac' , 'Grand Prix'), (1981 , 'Plymouth' , 'Reliant'), (1981 , 'Dodge' , 'Aries'), (1981 , 'Chevrolet' , 'Camaro'),
                                                 (1981 , 'Pontiac' , 'Grand Prix'), (1981 , 'Mercedes-Benz' , 'W126'), (1982 , 'Pontiac' , 'Grand Prix'),
                                                 (1982 , 'Chevrolet' , 'Camaro'), (1982 , 'Toyota' , 'Celica'), (1983 , 'Ford' , 'Thunderbird'), (1983 , 'Ford' , 'Mustang'),
                                                 (1983 , 'Mercedes-Benz' , 'W126'), (1983 , 'Pontiac' , '6000'), (1983 , 'Pontiac' , 'Sunbird'), (1983 , 'Pontiac' , 'Grand Prix'),
                                                 (1983 , 'Toyota' , 'Celica'), (1983 , 'Chevrolet' , 'Caprice'), (1983 , 'Honda' , 'Accord'), (1983 , 'Renault' , 'Alliance'),
                                                 (1983 , 'Mazda' , '626'), (1983 , 'Mazda' , 'RX-7'), (1983 , 'Volkswagen' , 'Golf'), (1983 , 'Porsche' , '944'),
                                                 (1984 , 'Pontiac' , '1000'), (1984 , 'Pontiac' , '6000'), (1984 , 'Pontiac' , 'Sunbird'), (1984 , 'Pontiac' , 'Bonneville'),
                                                 (1984 , 'Pontiac' , 'Firebird'), (1984 , 'Pontiac' , 'Parisienne'), (1984 , 'Pontiac' , 'Grand Prix'), (1984 , 'Pontiac' , 'Fiero')
                                                 , (1984 , 'Pontiac' , 'Firefly'), (1984 , 'Ford' , 'Bronco II'), (1984 , 'Ford' , 'Laser'), (1984 , 'Ford' , 'Thunderbird'),
                                                 (1984 , 'Ford' , 'EXP'), (1984 , 'Ford' , 'Ranger'), (1984 , 'Ford' , 'Escort'), (1984 , 'Ford' , 'Tempo'), (1984 , 'Ford' , 'LTD')
                                                 , (1984 , 'Ford' , 'F250'), (1984 , 'Ford' , 'F150'), (1984 , 'Ford' , 'E250'), (1984 , 'Ford' , 'LTD Crown Victoria'),
                                                 (1984 , 'Ford' , 'Bronco'), (1984 , 'Ford' , 'E150'), (1984 , 'Ford' , 'Mustang'), (1984 , 'Mitsubishi' , 'Space'),
                                                 (1984 , 'Mitsubishi' , 'Galant'), (1984 , 'Mitsubishi' , 'Mirage'), (1984 , 'Mitsubishi' , 'Starion'),
                                                 (1984 , 'Mitsubishi' , 'Pajero'), (1984 , 'Mitsubishi' , 'Cordia'), (1984 , 'Mitsubishi' , 'Tredia'), (1984 , 'Subaru' , 'Brat'),
                                                 (1984 , 'Mercedes-Benz' , 'S-Class'), (1984 , 'Mercedes-Benz' , 'E-Class'), (1984 , 'Mercedes-Benz' , 'W201'),
                                                 (1984 , 'Mercedes-Benz' , 'SL-Class'), (1984 , 'Mercury' , 'Topaz'), (1984 , 'Mercury' , 'Grand Marquis'),
                                                 (1984 , 'Mercury' , 'Lynx'), (1984 , 'Mercury' , 'Capri'), (1984 , 'Mercury' , 'Cougar'), (1984 , 'Mercury' , 'Marquis'),
                                                 (1984 , 'Lotus' , 'Esprit Turbo'), (1984 , 'Volkswagen' , 'Jetta'), (1984 , 'Volkswagen' , 'Golf'),
                                                 (1984 , 'Volkswagen' , 'Vanagon'), (1984 , 'Volkswagen' , 'Scirocco'), (1984 , 'Volkswagen' , 'Quantum'),
                                                 (1984 , 'Buick' , 'Electra'), (1984 , 'Buick' , 'Century'), (1984 , 'Buick' , 'Skyhawk'), (1984 , 'Lincoln' , 'Town Car'),
                                                 (1984 , 'Lincoln' , 'Continental'), (1984 , 'Lincoln' , 'Mark VII'), (1984 , 'Maserati' , 'Quattroporte'),
                                                 (1984 , 'Maserati' , 'Biturbo'), (1984 , 'Saab' , '900'), (1984 , 'Audi' , '5000S'), (1984 , 'Honda' , 'Accord'),
                                                 (1984 , 'Honda' , 'CR-X'), (1984 , 'Honda' , 'Prelude'), (1984 , 'Porsche' , '944'), (1984 , 'Dodge' , 'Daytona'),
                                                 (1984 , 'Suzuki' , 'SJ 410'), (1984 , 'Mazda' , '626'), (1984 , 'Mazda' , 'GLC'), (1984 , 'Mazda' , 'RX-7'),
                                                 (1984 , 'Toyota' , 'Celica'), (1984 , 'Chevrolet' , 'Corvette'), (1985 , 'Buick' , 'Somerset'), (1985 , 'Buick' , 'Electra'),
                                                 (1985 , 'Buick' , 'Century'), (1985 , 'Buick' , 'LeSabre'), (1985 , 'Buick' , 'Riviera'), (1985 , 'Buick' , 'Skyhawk'),
                                                 (1985 , 'Buick' , 'Regal'), (1985 , 'Buick' , 'Skylark'), (1985 , 'Ford' , 'E-Series'), (1985 , 'Ford' , 'Bronco II'),
                                                 (1985 , 'Ford' , 'Bronco'), (1985 , 'Ford' , 'Tempo'), (1985 , 'Ford' , 'F-Series'), (1985 , 'Ford' , 'Laser'),
                                                 (1985 , 'Ford' , 'LTD Crown Victoria'), (1985 , 'Ford' , 'Ranger'), (1985 , 'Ford' , 'Mustang'), (1985 , 'Ford' , 'LTD'),
                                                 (1985 , 'Ford' , 'Escort'), (1985 , 'Ford' , 'EXP'), (1985 , 'Ford' , 'Thunderbird'), (1985 , 'Mitsubishi' , 'Starion'),
                                                 (1985 , 'Mitsubishi' , 'Mirage'), (1985 , 'Mitsubishi' , 'Truck'), (1985 , 'Mitsubishi' , 'Chariot'),
                                                 (1985 , 'Mitsubishi' , 'Tredia'), (1985 , 'Mitsubishi' , 'Cordia'), (1985 , 'Mitsubishi' , 'Pajero'),
                                                 (1985 , 'Volkswagen' , 'Jetta'), (1985 , 'Volkswagen' , 'GTI'), (1985 , 'Volkswagen' , 'Cabriolet'), (1985 , 'Volkswagen' , 'Golf')
                                                 , (1985 , 'Volkswagen' , 'Passat'), (1985 , 'Volkswagen' , 'Scirocco'), (1985 , 'Volkswagen' , 'Type 2'),
                                                 (1985 , 'Mercedes-Benz' , 'W201'), (1985 , 'Mercedes-Benz' , 'S-Class'), (1985 , 'Mercedes-Benz' , 'E-Class'),
                                                 (1985 , 'Mercedes-Benz' , 'SL-Class');


INSERT INTO `vehicle`(license_plate, avg_consumption, brand_name, model_id, fuel) VALUES ("21-AA-12" , 10.1 , 'Chevrolet' , 97, 'GAS_95');