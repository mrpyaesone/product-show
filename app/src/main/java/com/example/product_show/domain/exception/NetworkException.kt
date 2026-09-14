package com.example.product_show.domain.exception

import java.io.IOException

class NetworkException(code: String, errMessage: String) : IOException(errMessage)