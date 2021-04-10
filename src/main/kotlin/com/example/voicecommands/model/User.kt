package com.example.voicecommands.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
data class User(
    private var username: String,

    private var password: String,
) : UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    @OneToMany(mappedBy = "owner")
    var ownedNotes: MutableSet<Note> = mutableSetOf()

    @ManyToMany
    var sharedNotes: MutableSet<Note> = mutableSetOf()

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        AuthorityUtils.commaSeparatedStringToAuthorityList("USER")

    override fun getUsername(): String = username
    override fun getPassword(): String = password
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}
