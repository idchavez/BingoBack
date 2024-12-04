package com.bingoback;

import com.bingoback.auth.persistence.entity.Permiso;
import com.bingoback.auth.persistence.entity.Rol;
import com.bingoback.auth.persistence.entity.RoleEnum;
import com.bingoback.auth.persistence.entity.Usuario;
import com.bingoback.auth.persistence.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class BingobackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BingobackApplication.class, args);
	}

	/*
	@Bean
	CommandLineRunner init(UsuarioRepository usuarioRepository) {
		return args -> {


			Permiso createPermission = Permiso.builder()
					.nombre("CREATE")
					.build();

			Permiso readPermission = Permiso.builder()
					.nombre("READ")
					.build();

			Permiso updatePermission = Permiso.builder()
					.nombre("UPDATE")
					.build();

			Permiso deletePermission = Permiso.builder()
					.nombre("DELETE")
					.build();

			Rol roleAdmin = Rol.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permisosLista(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			Rol roleUser = Rol.builder()
					.roleEnum(RoleEnum.USER)
					.permisosLista(Set.of(readPermission))
					.build();

			Usuario userId = Usuario.builder()
					.username("id")
					.password("$2a$10$u4TtXj0nC86UWWg8DtUoRO/F2tF56AJAW6YpjLwkr6kynG8s2hhC6") //123
					.isEnable(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();

			Usuario userId2 = Usuario.builder()
					.username("id2")
					.password("$2a$10$u4TtXj0nC86UWWg8DtUoRO/F2tF56AJAW6YpjLwkr6kynG8s2hhC6") //123
					.isEnable(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleUser))
					.build();

			Usuario userId3 = Usuario.builder()
					.username("id3")
					.password("$2a$10$u4TtXj0nC86UWWg8DtUoRO/F2tF56AJAW6YpjLwkr6kynG8s2hhC6") //123
					.isEnable(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleUser))
					.build();

			usuarioRepository.saveAll(List.of(userId, userId2, userId3));
		};
	}*/
}
