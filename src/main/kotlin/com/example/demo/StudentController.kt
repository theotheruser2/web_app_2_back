package com.example.demo

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@CrossOrigin
@RestController
class StudentController {
    private var comments = mutableListOf<Comment>()

    @GetMapping("/allComments")
    fun getAllComments(): MutableList<Comment>{
        return comments
    }

    @PostMapping("/addComment")
    fun putComment(@RequestBody comm: Comment): String{
        if(comm.author.length > 300 || comm.comment.length > 1000){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Error. Author field cannot exceed 300 characters. Comment field cannot exceed 1000 characters.")
        }
        else if(comm.author === "" || comm.comment === ""){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Error. Cannot send empty comments.")
        }
        else {
            println(comm)
            comments.add(comm)
            return "Comment saved."
        }
    }


}