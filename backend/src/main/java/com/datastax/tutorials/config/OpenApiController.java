package com.datastax.tutorials.config;

import static org.springdoc.core.Constants.SWAGGER_UI_PATH;
import static org.springframework.util.AntPathMatcher.DEFAULT_PATH_SEPARATOR;
import static org.springframework.web.servlet.view.UrlBasedViewResolver.REDIRECT_URL_PREFIX;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OpenApiController {

    public static final String HTTP_OK                = String.valueOf(HttpURLConnection.HTTP_OK);
    public static final String HTTP_CREATED           = String.valueOf(HttpURLConnection.HTTP_CREATED);
    String HTTP_ACCEPTED          = String.valueOf(HttpURLConnection.HTTP_ACCEPTED);
    String HTTP_NOT_AUTHORITATIVE = String.valueOf(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
    String HTTP_NO_CONTENT        = String.valueOf(HttpURLConnection.HTTP_NO_CONTENT);
    String HTTP_RESET             = String.valueOf(HttpURLConnection.HTTP_RESET);
    String HTTP_PARTIAL           = String.valueOf(HttpURLConnection.HTTP_PARTIAL);
    String HTTP_MULT_CHOICE       = String.valueOf(HttpURLConnection.HTTP_MULT_CHOICE);
    String HTTP_MOVED_PERM        = String.valueOf(HttpURLConnection.HTTP_MOVED_PERM);
    String HTTP_MOVED_TEMP        = String.valueOf(HttpURLConnection.HTTP_MOVED_TEMP);
    String HTTP_SEE_OTHER         = String.valueOf(HttpURLConnection.HTTP_SEE_OTHER);
    String HTTP_NOT_MODIFIED      = String.valueOf(HttpURLConnection.HTTP_NOT_MODIFIED);
    String HTTP_USE_PROXY         = String.valueOf(HttpURLConnection.HTTP_USE_PROXY);
    String HTTP_BAD_REQUEST       = String.valueOf(HttpURLConnection.HTTP_BAD_REQUEST);
    String HTTP_UNAUTHORIZED      = String.valueOf(HttpURLConnection.HTTP_UNAUTHORIZED);
    String HTTP_PAYMENT_REQUIRED  = String.valueOf(HttpURLConnection.HTTP_PAYMENT_REQUIRED);
    String HTTP_FORBIDDEN         = String.valueOf(HttpURLConnection.HTTP_FORBIDDEN);
    String HTTP_NOT_FOUND         = String.valueOf(HttpURLConnection.HTTP_NOT_FOUND);
    String HTTP_BAD_METHOD        = String.valueOf(HttpURLConnection.HTTP_BAD_METHOD);
    String HTTP_NOT_ACCEPTABLE    = String.valueOf(HttpURLConnection.HTTP_NOT_ACCEPTABLE);
    String HTTP_PROXY_AUTH        = String.valueOf(HttpURLConnection.HTTP_PROXY_AUTH);
    String HTTP_CLIENT_TIMEOUT    = String.valueOf(HttpURLConnection.HTTP_CLIENT_TIMEOUT);
    String HTTP_CONFLICT          = String.valueOf(HttpURLConnection.HTTP_CONFLICT);
    String HTTP_GONE              = String.valueOf(HttpURLConnection.HTTP_GONE);
    String HTTP_LENGTH_REQUIRED   = String.valueOf(HttpURLConnection.HTTP_LENGTH_REQUIRED);
    String HTTP_PRECON_FAILED     = String.valueOf(HttpURLConnection.HTTP_PRECON_FAILED);
    String HTTP_ENTITY_TOO_LARGE  = String.valueOf(HttpURLConnection.HTTP_ENTITY_TOO_LARGE);
    String HTTP_REQ_TOO_LONG      = String.valueOf(HttpURLConnection.HTTP_REQ_TOO_LONG);
    String HTTP_UNSUPPORTED_TYPE  = String.valueOf(HttpURLConnection.HTTP_UNSUPPORTED_TYPE);
    String HTTP_INTERNAL_ERROR    = String.valueOf(HttpURLConnection.HTTP_INTERNAL_ERROR);
    String HTTP_NOT_IMPLEMENTED   = String.valueOf(HttpURLConnection.HTTP_NOT_IMPLEMENTED);
    String HTTP_BAD_GATEWAY       = String.valueOf(HttpURLConnection.HTTP_BAD_GATEWAY);
    String HTTP_UNAVAILABLE       = String.valueOf(HttpURLConnection.HTTP_UNAVAILABLE);
    String HTTP_GATEWAY_TIMEOUT   = String.valueOf(HttpURLConnection.HTTP_GATEWAY_TIMEOUT);
    String HTTP_VERSION           = String.valueOf(HttpURLConnection.HTTP_VERSION);
    
    @Value(SWAGGER_UI_PATH)
    private String swaggerUiPath;

    // @GetMapping(DEFAULT_PATH_SEPARATOR)
    public String index() {
        return REDIRECT_URL_PREFIX + swaggerUiPath;
    }
}