#Build the Web Project Framework
#(Intellij IDEA Part)
##Prepare(Only in this guide)
####JDK:1.8
####IDE:Intellij IDEA
####Server:Tomcat 8.5
####Database:MySQL 5.5

##Web Project Framework
**MVC：**
![MVC的原理图](https://images2015.cnblogs.com/blog/249993/201702/249993-20170207135959401-404841652.png)
**Spring MVC：**
![SpringMVC的原理图](https://images2015.cnblogs.com/blog/249993/201702/249993-20170207140151791-1932120070.png)



##Frist Step
1. New a maven project and choose `create from archetype`<br>
2. Give groupid and artifactid<br>
3. Give project name and project location<br>
4. Right click this project and choose **add Framework Support**<br>
5. Add ***Web Support*** and choose create `web.xml`



##Second Step
1. Open the `pom.xml` and add dependencies follow these

		<dependencies>
        	<!--Spring Support-->
        	<dependency>
            	<groupId>org.springframework</groupId>
            	<artifactId>spring-core</artifactId>
            	<version>4.3.8.RELEASE</version>
        	</dependency>
	        <dependency>
	            <groupId>org.springframework</groupId>
	            <artifactId>spring-context</artifactId>
	            <version>4.3.8.RELEASE</version>
	        </dependency>
	        <dependency>
	            <groupId>org.springframework</groupId>
	            <artifactId>spring-aop</artifactId>
	            <version>4.3.8.RELEASE</version>
	        </dependency>
	        <dependency>
	            <groupId>org.springframework</groupId>
	            <artifactId>spring-beans</artifactId>
	            <version>4.3.8.RELEASE</version>
	        </dependency>
	        <dependency>
	            <groupId>org.springframework</groupId>
	            <artifactId>spring-aspects</artifactId>
	            <version>4.3.8.RELEASE</version>
	        </dependency>
	        <dependency>
	            <groupId>org.springframework</groupId>
	            <artifactId>spring-jdbc</artifactId>
	            <version>4.3.8.RELEASE</version>
	        </dependency>
	        <dependency>
	            <groupId>org.springframework</groupId>
	            <artifactId>spring-web</artifactId>
	            <version>4.3.8.RELEASE</version>
	        </dependency>
	        <dependency>
	            <groupId>org.springframework</groupId>
	            <artifactId>spring-webmvc</artifactId>
	            <version>4.3.8.RELEASE</version>
	        </dependency>
	        <dependency>
	            <groupId>org.springframework</groupId>
	            <artifactId>spring-test</artifactId>
	            <version>4.3.8.RELEASE</version>
	        </dependency>
	        <dependency>
	            <groupId>javax.servlet</groupId>
	            <artifactId>jstl</artifactId>
	            <version>1.2</version>
	        </dependency>
	
	        <!--Mysql Support-->
	        <dependency>
	            <groupId>mysql</groupId>
	            <artifactId>mysql-connector-java</artifactId>
	            <version>5.1.37</version>
	        </dependency>
	
	        <!-- Hibernate  Support-->
	        <dependency>
	            <groupId>org.hibernate</groupId>
	            <artifactId>hibernate-core</artifactId>
	            <version>5.2.1.Final</version>
	        </dependency>
	        <!-- c3p0数据源 -->
	        <dependency>
	            <groupId>com.mchange</groupId>
	            <artifactId>c3p0</artifactId>
	            <version>0.9.5-pre10</version>
	        </dependency>
	        <dependency>
	            <groupId>org.springframework</groupId>
	            <artifactId>spring-orm</artifactId>
	            <version>4.3.8.RELEASE</version>
	        </dependency>
	    </dependencies>

2. Open the `resources` directory
3. create `applicationContext.xml`、`dispatcherServlet.xml`、`jdbc.properties`

**applicationContext.xml**

	<?xml version="1.0" encoding="UTF-8"?>
	<!--suppress ALL -->
	<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.0.xsd
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd
        http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.0.xsd">

	    <!--引入数据源配置文件-->
	    <context:property-placeholder location="classpath:jdbc.properties" />
	    <!-- 配置数据源 -->
	    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource"
	          destroy-method="close">
	        <property name="driverClass">
	            <value>${jdbc.driverClassName}</value>
	        </property>
	        <property name="jdbcUrl">
	            <value>${jdbc.url}</value>
	        </property>
	        <property name="user">
	            <value>${jdbc.username}</value>
	        </property>
	        <property name="password">
	            <value>${jdbc.password}</value>
	        </property>
	        <!--连接池中保留的最小连接数。 -->
	        <property name="minPoolSize">
	            <value>5</value>
	        </property>
	        <!--连接池中保留的最大连接数。Default: 15 -->
	        <property name="maxPoolSize">
	            <value>30</value>
	        </property>
	        <!--初始化时获取的连接数，取值应在minPoolSize与maxPoolSize之间。Default: 3 -->
	        <property name="initialPoolSize">
	            <value>10</value>
	        </property>
	        <!--最大空闲时间,60秒内未使用则连接被丢弃。若为0则永不丢弃。Default: 0 -->
	        <property name="maxIdleTime">
	            <value>60</value>
	        </property>
	        <!--当连接池中的连接耗尽的时候c3p0一次同时获取的连接数。Default: 3 -->
	        <property name="acquireIncrement">
	            <value>5</value>
	        </property>
	        <!--JDBC的标准参数，用以控制数据源内加载的PreparedStatements数量。但由于预缓存的statements 属于单个connection而不是整个连接池。所以设置这个参数需要考虑到多方面的因素。
	            如果maxStatements与maxStatementsPerConnection均为0，则缓存被关闭。Default: 0 -->
	        <property name="maxStatements">
	            <value>0</value>
	        </property>
	        <!--每60秒检查所有连接池中的空闲连接。Default: 0 -->
	        <property name="idleConnectionTestPeriod">
	            <value>60</value>
	        </property>
	        <!--定义在从数据库获取新连接失败后重复尝试的次数。Default: 30 -->
	        <property name="acquireRetryAttempts">
	            <value>30</value>
	        </property>
	        <!--获取连接失败将会引起所有等待连接池来获取连接的线程抛出异常。但是数据源仍有效 保留，并在下次调用getConnection()的时候继续尝试获取连接。如果设为true，那么在尝试
	            获取连接失败后该数据源将申明已断开并永久关闭。Default: false -->
	        <property name="breakAfterAcquireFailure">
	            <value>true</value>
	        </property>
	        <!--因性能消耗大请只在需要的时候使用它。如果设为true那么在每个connection提交的 时候都将校验其有效性。建议使用idleConnectionTestPeriod或automaticTestTable
	            等方法来提升连接测试的性能。Default: false -->
	        <property name="testConnectionOnCheckout">
	            <value>false</value>
	        </property>
	        <property name="testConnectionOnCheckin">
	            <value>false</value>
	        </property>
	    </bean>
	
	    <!--配置hibernate sessionFacotry-->
	    <bean id="sessionFactory" class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
	        <property name="dataSource" ref="dataSource"/>
	        <property name="hibernateProperties">
	            <props>
	                <prop key="hibernate.dialect">org.hibernate.dialect.MySQL5InnoDBDialect</prop>
	                <prop key="hibernate.show_sql">true</prop>
	                <prop key="hibernate.format_sql">true</prop>
	            </props>
	        </property>
	        <!--自动扫描注解的方式配置hibernate 类文件-->
	        <property name="packagesToScan">
	            <list>
	                <value>entity</value>
	            </list>
	        </property>
	    </bean>
	
	    <!--配置事务管理器-->
	    <bean id="txManager" class="org.springframework.orm.hibernate5.HibernateTransactionManager">
	        <property name="sessionFactory" ref="sessionFactory"/>
	    </bean>
	
	    <!-- 配置事务通知属性 -->
	    <tx:advice id="txAdvice" transaction-manager="txManager">
	        <!-- 定义事务传播属性 -->
	        <tx:attributes>
	            <tx:method name="insert*" propagation="REQUIRED" />
	            <tx:method name="count*" propagation="REQUIRED" />
	            <tx:method name="update*" propagation="REQUIRED" />
	            <tx:method name="edit*" propagation="REQUIRED" />
	            <tx:method name="save*" propagation="REQUIRED" />
	            <tx:method name="add*" propagation="REQUIRED" />
	            <tx:method name="new*" propagation="REQUIRED" />
	            <tx:method name="set*" propagation="REQUIRED" />
	            <tx:method name="remove*" propagation="REQUIRED" />
	            <tx:method name="delete*" propagation="REQUIRED" />
	            <tx:method name="change*" propagation="REQUIRED" />
	            <tx:method name="get*" propagation="REQUIRED" read-only="true" />
	            <tx:method name="find*" propagation="REQUIRED" read-only="true" />
	            <tx:method name="load*" propagation="REQUIRED" read-only="true" />
	            <tx:method name="*" propagation="REQUIRED" read-only="true" />
	        </tx:attributes>
	    </tx:advice>
	
	    <!-- 配置事务切面 -->
	    <aop:config>
	        <aop:pointcut id="serviceOperation"
	                      expression="execution(* service.*.*(..))" />
	        <aop:advisor advice-ref="txAdvice" pointcut-ref="serviceOperation" />
	    </aop:config>
	
	    <!-- 自动加载构建bean -->
	    <context:component-scan base-package="service" />
	    <context:component-scan base-package="dao" />
	</beans>


**dispatcherServlet.xml**

	<?xml version="1.0" encoding="UTF-8"?>
	<beans 
		xsi:schemaLocation="
			http://www.springframework.org/schema/beans 
			http://www.springframework.org/schema/beans/spring-beans-3.0.xsd 
			http://www.springframework.org/schema/mvc 
			http://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd 
			http://www.springframework.org/schema/context 
			http://www.springframework.org/schema/context/spring-context-3.0.xsd"
	     xmlns:mvc="http://www.springframework.org/schema/mvc"
	     xmlns:context="http://www.springframework.org/schema/context"
	     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	     xmlns="http://www.springframework.org/schema/beans" 
	default-lazy-init="true">
	<!-- 使用注解的包，包括子集 -->
	    <context:component-scan base-package="controller"/>
	    <mvc:resources location="/static/" mapping="/static/**"/>
	    <mvc:annotation-driven/>
	    <!-- 定义跳转的文件的前后缀 ，视图模式配置 -->
	    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	        <property value="/WEB-INF/pages/" name="prefix"/>
	        <property value=".jsp" name="suffix"/>
	    </bean>
	</beans>


**jdbc.properties**

	jdbc.driverClassName=com.mysql.jdbc.Driver
	jdbc.url=jdbc:mysql://localhost:3306/support
	jdbc.username=root
	jdbc.password=1111

4. Open `web` directory and create a `static` directory<br>
5. Under this directory create `css`,`img`,`js` and  so on<br>
6. In `web.xml` ,we do the following configuration:

		<?xml version="1.0" encoding="UTF-8"?>
		<!--suppress ALL -->
		<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
		         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
		         version="3.1">
		    <!--配置文件路径-->
		    <context-param>
		        <param-name>contextConfigLocation</param-name>
		        <param-value>classpath:applicationContext.xml;classpath:dispatcherServlet.xml</param-value>
		    </context-param>
		    <!--default page-->
		    <welcome-file-list>
		        <welcome-file>index.jsp</welcome-file>
		    </welcome-file-list>
		    <!--servlet-->
		    <servlet>
		        <servlet-name>dispatcherServlet</servlet-name>
		        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		        <init-param>
		            <param-name>contextConfigLocation</param-name>
		            <param-value>classpath:dispatcherServlet.xml</param-value>
		        </init-param>
		    </servlet>
		    <servlet-mapping>
		        <servlet-name>dispatcherServlet</servlet-name>
		        <url-pattern>/</url-pattern>
		    </servlet-mapping>
		    <!--Listener-->
		    <listener>
		        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
		    </listener>
		    <!--EncodingFilter-->
		    <filter>
		        <filter-name>characterEncodingFilter</filter-name>
		        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
		        <init-param>
		            <param-name>encoding</param-name>
		            <param-value>utf-8</param-value>
		        </init-param>
		    </filter>
		</web-app>

##Third Step
1. Open `Settings` and choose `Build,Execution,Development`-`Application Servers`
2. Add your `Tomcat` Server

##Forth Step
1. Open Java directory and create `controller`,`dao`,`entity`,`service` directory<br>
2. In `entity` directory,create a `Customer` class and create corresponding table in your database


		@Entity
		@Table(name="customers")
		public class Customer implements Serializable {
			@Id
		    @Column(name="customerId")
		    private String id;
		    @Column(name="name")
		    private String name;
		    @Column(name="phone")
		    private String phone;
			public String getId() {
				return id;
			}
			public void setId(String id) {
				this.id = id;
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getPhone() {
				return phone;
			}
			public void setPhone(String phone) {
				this.phone = phone;
			}
		}

3. In `dao` directory,create `BaseDao`,`CustomerDao` interface and `BaseDaoImpl`,`CustomerDaoImpl` class

	**BaseDao**

		public interface BaseDao<T> {  
		    public void save(T entity);  
		    public void update(T entity);  
		    public void delete(Serializable id);
		    public T findById(Serializable id);
		    public List<T> findByHQL(String hql, Object... params);
		    public List<T> findAllByHQL(String hql);
		    public List<T> listPage(String hql,int offset, int rows);
		} 

	**CustomerDao**

		public interface CustomerDao extends BaseDao<Customer> {
		    public List<Customer> listAllCustomers();
		    public List<Customer> listPageCustomers(int offset, int rows);
		    public void save(Customer model);
		    public void delete(String id);
		    public void update(Customer model);
		}

	**BaseDaoImpl**

		public class BaseDaoImpl<T> implements BaseDao<T> {
		    private Class<T> clazz;
		    public BaseDaoImpl() {  
		        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		        clazz = (Class<T>) type.getActualTypeArguments()[0];
		    }  
		    @Resource
		    private SessionFactory sessionFactory;
		    private Session getSession() {
		        return this.sessionFactory.getCurrentSession();
		    }
		    public void save(T entity) {  
		        this.getSession().save(entity);  
		    }  
		    public void update(T entity) {  
		        this.getSession().update(entity);  
		    }  
		    public void delete(Serializable id) {
		        this.getSession().delete(this.findById(id));  
		    }  
		    public T findById(Serializable id) {
		        return (T) this.getSession().get(this.clazz, id);  
		    }  
		    public List<T> findByHQL(String hql, Object... params) {
		        Query query = this.getSession().createQuery(hql);  
		        for (int i = 0; params != null && i < params.length; i++) {  
		            query.setParameter(i, params);  
		        }  
		        return query.list();  
		    }  
		    public List<T> findAllByHQL(String hql) {
		        Query query = this.getSession().createQuery(hql);
		        System.out.println("[query]="+query.list().toString());
		        return query.list();  
		    }
		    @Override
		    public List<T> listPage(String hql,int offset, int rows) {
		        Query query = this.getSession().createQuery(hql);
		        query.setFirstResult(offset);
		        query.setMaxResults(rows);
		        return query.list();
		    }
		}

	**CustomerDaoImpl**

		@Repository("customerDao")
		public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao{
		    public List<Customer> listAllCustomers(){
		        String hql="from Customer";
		        List<Customer> list = this.findAllByHQL(hql);
		        return list;
		    }
		    @Override
		   	public List<Customer> listPageCustomers(int offset, int rows) {
		        String hql="from Customer";
		        List<Customer> list = this.listPage(hql,offset,rows);
		   		return list;
		   	}
		    public void save(Customer entity){
		       super.save(entity);
		    }
		    public void delete(String id){
		        super.delete(id);
		    }
		    public void update(Customer entity){
		       super.update(entity);
		    }
		}

4. In `service` directory,create a `CustomerService` class


		@Service
		public class CustomerService {
		    @Autowired
		    CustomerDao customerDao;
		    public List<Customer> listAllCustomers(){
		        return customerDao.listAllCustomers();
		    }
		    public List<Customer> listPageUser(int offset, int rows){
		        return customerDao.listPageCustomers(offset,rows);
		    }
		    public void save(Customer model){
		    	customerDao.save(model);
		    }
		    public void update(Customer model){
		    	customerDao.update(model);
		    }
		    public void delete(String id){
		    	customerDao.delete(id);
		    }
		}

5. In `controller` directory,create a `CustomerController` class

		@Controller
		@RequestMapping("/customer")
		public class CustomerController {
		
		    @Autowired
		    CustomerService customerService;
		
		    @RequestMapping("/index")
		    public String index(ModelMap map) {
		        List<Customer> customers = customerService.listAllCustomers();
		        map.put("list", customers);
		        return "customers";
		    }
		
		    @RequestMapping("/delete")
		    public String deleteCustomerByID(String id) {
		        customerService.delete(id);
		        return "redirect:index";
		    }
		
		    @RequestMapping("/update")
		    public String updateCustomerByID(String id, @RequestParam(value = "name") String name, @RequestParam(value = "phone") String phone) {
		        Customer cus = new Customer();
		        cus.setId(id);
		        cus.setName(name);
		        cus.setPhone(phone);
		        customerService.update(cus);
		        return "redirect:index";
		    }
		
		    @RequestMapping("/add")
		    public String addCustomerByID(@RequestParam(value = "id") String id, @RequestParam(value = "name") String name, @RequestParam(value = "phone") String phone) {
		        Customer cus = new Customer();
		        cus.setId(id);
		        cus.setName(name);
		        cus.setPhone(phone);
		        customerService.save(cus);
		        return "redirect:index";
		    }
		}

##Sixth Step
1. In `WEB-INF`-`pages` directory,create a `customers.jsp`

	**customers.jsp**
	
		<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		<!DOCTYPE html>
		<html>
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title></title>
		<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
		</head>
		<body>
		<div class="container">
			<table class="table">
			<tbody>
			<tr>
				<td class="text-left" hidden="none">id：<input id = "add_id" type="text"/></td>
				<td class="text-left" hidden="none">name:<input id = "add_name" type="text"/></td>
				<td class="text-left" hidden="none">phone:<input id = "add_phone" type="text"/></td>
				<td><input type="button" class="btn btn-primary" id="add" value="添加"/></td>
			</tr>
			</tbody>
			</table>
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Phone</th>
						<th>Opt</th>
					</tr>
				</thead>
				<tbody>
				  
					<c:forEach var="customer" items="${list}" varStatus="status">
						<tr>
							<td>${customer.id}</td>
							<td id="name${customer.id}">${customer.name}</td>
							<td id="phone${customer.id}">${customer.phone}</td>
							<td>
								<input id ="${customer.id}" type="button" class="btn btn-primary" value="修改" />
								<input type="button" class="btn btn-warning" value="删除" onclick="location.href='delete?id=${customer.id}'"/>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		
		</div>
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.js"
			type="text/javascript" charset="utf-8"></script>
		<script>
		    $(".btn.btn-primary").click(
		        function(){
		            var id = this.id;
		            var val = this.value;
		            if(val === "修改"){
						$("#"+id).val("保存");
		                $("#name"+id).html("<input id = \"name\" type=\"text\"/>");
		                $("#phone"+id).html("<input id = \"phone\" type=\"text\"/>");
					}else if(val === "保存"){
		                var name = $("#name").val();
		                var phone = $("#phone").val();
		                location.href = "update?id="+id+"&name="+name+"&phone="+phone;
					}else if (val === "添加") {
		                $(".text-left").show();
		                $("#add").val("提交");
		            } else if (val === "提交") {
		                var add_id = $("#add_id").val();
		                var add_name = $("#add_name").val();
		                var add_phone = $("#add_phone").val();
		                location.href = "add?id="+add_id+"&name="+add_name+"&phone="+add_phone;
		            }
		        });
		</script>
		</body>
		</html>

##Final Step
1. Start your project.










