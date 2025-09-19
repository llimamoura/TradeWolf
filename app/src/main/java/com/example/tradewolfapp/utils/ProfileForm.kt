package com.example.tradewolfapp.utils

fun isValidFullName(name: String): Boolean = name.isNotBlank()

fun isValidCPF(cpf: String): Boolean = cpf.length == 14

fun isValidEmail(email: String): Boolean = email.isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

fun isValidPhoneNumber(phone: String): Boolean = phone.length >= 13

fun isFormValid(
    fullName: String,
    cpf: String,
    email: String,
    phone: String
): Boolean {
    return isValidFullName(fullName) &&
           isValidCPF(cpf) &&
           isValidEmail(email) &&
           isValidPhoneNumber(phone)
}
