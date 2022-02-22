package com.example.search_filteramproom_paging.model

object UserUtil {
    fun checkEmptyOrNull(firstName:String,lastName:String,age:String): String{
        var errorMessage =""
        when {
            firstName.isNullOrEmpty() -> {
                errorMessage ="please enter the first name"
            }
            lastName.isNullOrEmpty() -> {
                errorMessage ="please enter the last name"
            }
            age.isNullOrEmpty() -> {
                errorMessage ="please enter the age"
            }
            age.length>4->{
                errorMessage ="Please enter valid age"
            }
        }
        return errorMessage
    }


}