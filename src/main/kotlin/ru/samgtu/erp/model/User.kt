package ru.samgtu.erp.model

import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @Column(name = "login", nullable = false)
        var login: String,

        @Column(name = "password", nullable = false)
        var password: String,

        @Column(name = "first_name", nullable = false)
        var firstName: String,

        @Column(name = "second_name")
        var secondName: String?,

        @Column(name = "surname", nullable = false)
        var surname: String,

        @Column(name = "is_deleted")
        var isDeleted: Boolean = false,

        @ManyToMany
        @JoinTable(
                name = "users_roles",
                joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
        )
        var roles: Collection<Role>? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "organization_id")
        var organization: Organization? = null
)