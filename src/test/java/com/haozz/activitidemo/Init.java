package java.com.haozz.activitidemo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author haozhezhe@yunquna.com
 * @date 15:20 2020-08-30
 */
@SpringBootTest
public class Init {

    @Test
    public void init() {

        // 生成ProcessEngine
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();

        // processEngine是activiti的核心
        System.out.println(processEngine);

        /**
         * 运行后，会在当前连接的数据库中生成25张activiti的表
         */
    }
}
