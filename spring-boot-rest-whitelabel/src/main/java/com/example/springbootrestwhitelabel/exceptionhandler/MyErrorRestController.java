package com.example.springbootrestwhitelabel.exceptionhandler;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Need to implement ErrorController
@RestController
public class MyErrorRestController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<ErrorResponse> exceptionHandler(HttpServletRequest request) {

        // Instantiate ErrorResponse and HttpStatus
        ErrorResponse errorResponse = new ErrorResponse();
        HttpStatus httpStatus = null;

        // Get status code
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        // Check status code
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                errorResponse.setStatus(statusCode);
                errorResponse.setMessage("What are you looking for?");
                errorResponse.setTimestamp(System.currentTimeMillis());
                httpStatus = HttpStatus.NOT_FOUND;
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                errorResponse.setStatus(statusCode);
                errorResponse.setMessage("Our server currently is under maintenance :(");
                errorResponse.setTimestamp(System.currentTimeMillis());
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }

        // Return the error response
        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}
