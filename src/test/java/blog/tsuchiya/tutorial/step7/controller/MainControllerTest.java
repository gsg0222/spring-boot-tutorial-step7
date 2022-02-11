package blog.tsuchiya.tutorial.step7.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class MainControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@BeforeEach
	void setup() {
		// // @formatter:off
		mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
	    		// Spring Securityの設定をテストに反映させるために必要
				.apply(springSecurity()).build();	}
		// @formatter:on

	@Test
	@DisplayName("ADNIN権限のユーザで/adminにアクセス")
	// 擬似的にADMINロールを持つadminがログインしている状態にする
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	void accessWithAdminToAdmin() throws Exception {
		// /adminはADMINロールを持つユーザなら正常に表示する
		this.mockMvc.perform(get("/admin")).andExpect(view().name("admin")).andExpect(status().isOk());
	}

	@Test
	@DisplayName("USER権限のユーザで/adminにアクセス")
	// 擬似的にUSERロールを持つuserがログインしている状態にする
	@WithMockUser(username = "user", roles = { "USER" })
	void accessWithUserToAdmin() throws Exception {
		// ADMINロールを持たないユーザはアクセス権を持たない
		this.mockMvc.perform(get("/admin")).andExpect(status().isForbidden());
	}

}
