package com.wassimmiladi.project_managment_tool.config.security;

public class SecurityConstants  {


    public  static  final String SIGN_UP_URLS = "/api/users/**" ;
    public  static  final String SECRET = "SecretKeyToGenJwt" ;
    public  static  final String  TOKEN_PREFIX= "Bearer " ;
    public  static  final String HEADER_STRING = "Authorization" ;
    public  static  final Long EXPRIRATION_TIME =  new Long(3000_000 );




}
