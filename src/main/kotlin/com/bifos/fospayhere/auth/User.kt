package com.bifos.fospayhere.auth

import javax.persistence.*

@Entity
@Table(name = "payhere_user")
class User(
    @Column
    val email: String,

    @Column
    val password: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null
}