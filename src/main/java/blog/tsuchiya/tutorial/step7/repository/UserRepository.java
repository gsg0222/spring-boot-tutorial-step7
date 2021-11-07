package blog.tsuchiya.tutorial.step7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.tsuchiya.tutorial.step7.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * 名前でユーザを検索。
	 * 命名規則に従ってインタフェースでメソッドを定義すると、
	 * JPAが実装を行ってくれる。
	 * @param name 検索するユーザ名
	 * @return ユーザ名に対応するユーザ
	 */
	User findByName(String name);
}
