package org.example;


import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;
import org.springframework.modulith.test.ApplicationModuleTest;

@ApplicationModuleTest
class ApplicationTest {
	/**
	 * 验证模块hua结构
	 */
	@Test
	void verifyApplicationModules() {
		ApplicationModules.of(Application.class).verify();
	}

	/**
	 * 基于 PlantUML 生成图表，将模块导出C4组件图
	 */
	@Test
	void createModuleDocumentation() {
		ApplicationModules modules = ApplicationModules.of(Application.class);
		new Documenter(modules)
			.writeDocumentation()
			.writeIndividualModulesAsPlantUml();
	}
}
