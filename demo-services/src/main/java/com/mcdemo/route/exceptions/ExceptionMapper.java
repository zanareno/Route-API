package com.mcdemo.route.exceptions;


import io.micrometer.core.instrument.util.StringUtils;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@EnableWebMvc
@ControllerAdvice
public class ExceptionMapper {

  private static final String DEFAULT_KEY = "message";
  private static final String PROBLEM_OCCURRED_DURING_DATA_PROCESSING_CHECK_INPUT_FOR_POTENTIAL_DATA_INCOHERENCES = "Problem occurred during data processing. Check input for potential data incoherences.";
  private static final String OPERATION_IS_NOT_AVAILABLE_FOR_THIS_ENDPOINT = "Operation is not available for this endpoint.";
  private static final String DEFAULT_ERROR_MESSAGE = "An error occurred during the process of your request";
  private static final String CONNECTION_REFUSED_CHECK_IF_SERVER_IS_UP_AND_RUNNING = "Connection refused. Check if server is up and running.";
  private static final String BAD_ENDPOINT_MESSAGE = "Probably endpoint was miss written. Check api documentation for more information.";
  private static Logger log = LoggerFactory.getLogger(ExceptionMapper.class);

  private ResponseEntity getResponseEntity(String message, Throwable ex, HttpStatus httpStatus) {
    log.error(ex.getMessage(), ex);
    HttpHeaders header = new HttpHeaders();
    header.setContentType(MediaType.APPLICATION_JSON);
    Map<String, String> responseBody = new HashMap<>();
    responseBody.put(DEFAULT_KEY, StringUtils.isBlank(message) ? ex.getMessage() : message);
    return new ResponseEntity<>(responseBody, header, httpStatus);
  }

  private ResponseEntity getResponseEntity(Throwable ex, HttpStatus httpStatus) {
    log.error(ex.getMessage(), ex);
    return getResponseEntity(ex.getMessage(), ex, httpStatus);
  }

  @ExceptionHandler(Throwable.class)
  public ResponseEntity handleThrowable(final Throwable ex) {
    log.error(ex.getMessage(), ex);
    return getResponseEntity(DEFAULT_ERROR_MESSAGE, ex, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(DemoException.class)
  public ResponseEntity handlePGManagementException(final Throwable ex) {
    log.error(ex.getMessage(), ex);
    return getResponseEntity(DEFAULT_ERROR_MESSAGE, ex, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(org.springframework.web.client.ResourceAccessException.class)
  public ResponseEntity handleResourceAccessException(final Throwable ex) {
    log.error(ex.getMessage(), ex);
    return getResponseEntity(CONNECTION_REFUSED_CHECK_IF_SERVER_IS_UP_AND_RUNNING, ex,
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ForbiddenRequestException.class)
  public ResponseEntity handleForbiddenRequestException(final ForbiddenRequestException ex) {
    return getResponseEntity(ex, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity handleNotFoundException(final NotFoundException ex) {
    return getResponseEntity(ex, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public ResponseEntity handleHttpMediaTypeNotSupportedException(final HttpMediaTypeNotSupportedException ex) {
    return getResponseEntity(ex, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity handleException(final NoHandlerFoundException ex) {
    log.error(ex.getMessage(), ex);
    return getResponseEntity(BAD_ENDPOINT_MESSAGE, ex, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(EmptyResultDataAccessException.class)
  public ResponseEntity handleEmptyResultDataAccessException(final EmptyResultDataAccessException ex) {
    return getResponseEntity(ex, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity handleHttpRequestMethodNotSupportedException(
      final HttpRequestMethodNotSupportedException ex) {
    return getResponseEntity(OPERATION_IS_NOT_AVAILABLE_FOR_THIS_ENDPOINT, ex,
        HttpStatus.METHOD_NOT_ALLOWED);
  }

  @ExceptionHandler(ServletRequestBindingException.class)
  public ResponseEntity handleServletRequestBindingException(final ServletRequestBindingException ex) {
    return getResponseEntity(ex, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity handleInvalidParameterException(final IllegalArgumentException ex) {
    return getResponseEntity(ex, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(IllegalStateException.class)
  public ResponseEntity handleIllegalStateException(final IllegalStateException ex) {
    return getResponseEntity(
        PROBLEM_OCCURRED_DURING_DATA_PROCESSING_CHECK_INPUT_FOR_POTENTIAL_DATA_INCOHERENCES, ex,
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NumberFormatException.class)
  public ResponseEntity handleNumberFormatException(final NumberFormatException ex) {
    return getResponseEntity(
        PROBLEM_OCCURRED_DURING_DATA_PROCESSING_CHECK_INPUT_FOR_POTENTIAL_DATA_INCOHERENCES, ex,
        HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(HttpStatusCodeException.class)
  public ResponseEntity handleHttpClientErrorException(final HttpStatusCodeException ex) {
    log.error(ex.getMessage(), ex);
    HttpHeaders header = new HttpHeaders();
    header.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<>(ex.getResponseBodyAsString(), header, ex.getStatusCode());
  }
}
