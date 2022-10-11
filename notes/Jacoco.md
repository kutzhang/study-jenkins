# Jacoco集成

## 一、Quarkus工程设置

POM

```xml
<dependencies>
....
    <dependency>
        <groupId>io.quarkus</groupId>
        <artifactId>quarkus-jacoco</artifactId>
        <scope>test</scope>
    </dependency>
....
</dependencies>
```

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.jacoco</groupId>
            <artifactId>jacoco-maven-plugin</artifactId>
            <version>0.8.8</version>
            <executions>
                <execution>
                    <id>default-prepare-agent</id>
                    <goals>
                        <goal>prepare-agent</goal>
                    </goals>
                    <configuration>
                        <exclClassLoaders>*QuarkusClassLoader</exclClassLoaders>
                        <destFile>${project.build.directory}/jacoco-quarkus.exec</destFile>
                        <append>true</append>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```



## 二、Jenkins 配置

### 1. 安装插件

`JaCoCo plugin`

### 2. Pipeline

```groovy
pipeline {
    agent {
        label "maven"
    }

    stages {
        stage('Checkout codes') {
            steps {
                milestone(ordinal: 1000, label: 'Get lock for project build')
                git url:'https://github.com/kutzhang/quarkus-get-started.git', branch:'main'
            }
        }

        // 1. unit test first, just static check
        // 2. jacoco
        stage('Unit Test') {
            steps{
                sh 'mvn test'
                jacoco(minimumLineCoverage: "5", maximumLineCoverage: '10', changeBuildStatus: true)
            }
        }
    }
}
```

**配置说明**

changeBuildStatus：表示当不通过时是否改变Pipeline的状态。

min：当小于该数值时，Jacoco表示coverage为0，表示不通过

max：当大于该数值时，Jacoco表示coverage为1，表示通过。

min < x < max：同样表示coverage为0，同样表示不通过

```
minimumInstructionCoverage('20')
minimumBranchCoverage('20')
minimumComplexityCoverage('20')
minimumLineCoverage('20')
minimumMethodCoverage('20')
minimumClassCoverage('20')
maximumInstructionCoverage('80')
maximumBranchCoverage('80')
maximumComplexityCoverage('80')
maximumLineCoverage('80')
maximumMethodCoverage('80')
maximumClassCoverage('80')
```

> 目前无法通过调用`jacoco`获取其运行状态，只能通过读取`target/jacoco-report/jacoco.xml`文件，分析该文件以获取状态。