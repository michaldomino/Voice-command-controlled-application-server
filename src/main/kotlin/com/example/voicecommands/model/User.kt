package com.example.voicecommands.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    @OneToMany(mappedBy = "owner")
    var ownedNotes: MutableSet<Note>,

    @ManyToMany
    var sharedNotes: MutableSet<Note>,

    private var username: String,

    private var password: String,
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = AuthorityUtils
        .commaSeparatedStringToAuthorityList("USER")

    override fun getUsername(): String = username
    override fun getPassword(): String = password
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}
