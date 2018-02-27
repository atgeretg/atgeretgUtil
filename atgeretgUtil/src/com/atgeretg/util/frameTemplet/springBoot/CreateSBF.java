package com.atgeretg.util.frameTemplet.springBoot;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.atgeretg.util.file.FileUtil;
import com.atgeretg.util.string.StrUtil;

/**
 * 生成SpringBoot大体框架<br>
 * 用该类生成框架前，必需要用generatorConfiguration工具生成mapper接口
 * 
 * @author atgeretg
 *
 */
public class CreateSBF {
	private static String filePath;
	private static final String mapperStr = "Mapper", Example = "Example", Service = "Service", baseService = "BaseService",
			baseMapper = "MapperBase", impl = "impl",Impl = "Impl", controller = "Controller", baseController = "BaseController",
			dataSource = "dataSource", DBConfigMain = "DBConfigMain", MyBatisConfigMain = "MyBatisConfigMain",
			java = ".java";
	private static String entityControllerPackage;
	private static String entityServersPackage;
	private static String entityPackage;
	private static String mapperPackage;
	private static String entityControllerPackageDot;
	private static String entityServersPackageDot;
	private static String entityPackageDot;
	private static String mapperPackageDot;
	private static String controllerBase;
	private static String controllerBaseDot;
	private static String dataSourcePackage;
	private static String dataSourcePackageDot;
	private static String encode = FileUtil.UTF8;
	// private String basePackage;
	private static String serversImplPackage;
	private static String ingoredService[] = {};// 过虑的service不用生成
	private static String ingoredController[] = {};// 过虑的controller不用生成
	private static String ingoredMapper[] = {};// 过虑的mapper不用处理

	/**
	 * 只输入基础包名，其它结构自动生成,<br>
	 * （如：输入：com.djotimes.nobodyService，service层包名字为：com.djotimes.nobodyService.service<br>
	 * controller层：com.djotimes.nobodyService.controller<br>
	 * entity层（默认）：com.djotimes.nobodyService.entity）
	 * 
	 * @param basePackage 基础包名
	 * @param entity
	 *            如果实体类层名不是叫"entity"，则必需填写实体类的包名，如实体类包名叫“model”则写“model”
	 */
	public CreateSBF(String basePackage, String entity) {
		basePackage = lastStrIsDot(basePackage, true);
		CreateSBF.entityControllerPackage = basePackage + controller.toLowerCase();
		CreateSBF.entityServersPackage = basePackage + Service.toLowerCase();
		CreateSBF.entityPackage = basePackage + (entity == null ? "entity" : entity);
		CreateSBF.mapperPackage = basePackage + mapperStr.toLowerCase();
		CreateSBF.dataSourcePackage = basePackage + dataSource;
		initParamName();
	}
	
	/**
	 * 指定包名生成springBoot框架结构
	 * @param entityControllerPackage controller层包名
	 * @param entityServersPackage service层包名
	 * @param entityPackage entity(实体类)层包名
	 * @param mapperPackage mapper层包名
	 * @param dataSourcePackage 数据源配置层包名
	 */
	public CreateSBF(String entityControllerPackage, String entityServersPackage, String entityPackage,
			String mapperPackage, String dataSourcePackage) {
		CreateSBF.entityControllerPackage = entityControllerPackage;
		CreateSBF.entityServersPackage = entityServersPackage;
		CreateSBF.entityPackage = entityPackage;
		CreateSBF.mapperPackage = mapperPackage;
		CreateSBF.dataSourcePackage = dataSourcePackage;
		initParamName();
	}

	/**
	 * 初始化路径参数
	 */
	private static void initParamName() {
		entityControllerPackage = lastStrIsDot(entityControllerPackage, false);
		entityServersPackage = lastStrIsDot(entityServersPackage, false);
		entityPackage = lastStrIsDot(entityPackage, false);
		mapperPackage = lastStrIsDot(mapperPackage, false);
		dataSourcePackage = lastStrIsDot(dataSourcePackage, false);
		entityControllerPackageDot = entityControllerPackage + ".";
		controllerBase = entityControllerPackageDot + "commons";
		entityServersPackageDot = entityServersPackage + ".";
		entityPackageDot = entityPackage + ".";
		mapperPackageDot = mapperPackage + ".";
		serversImplPackage = entityServersPackageDot + impl;
		controllerBaseDot = controllerBase + ".";
		dataSourcePackageDot = dataSourcePackage + ".";
		// System.out.println(CreateMVC.entityControllerPackage +"\n"+
		// CreateMVC.entityServersPackage +"\n"+
		// CreateMVC.entityPackage +"\n"+
		// CreateMVC.mapperPackage+"\n"+
		// CreateMVC.dataSourcePackage);
	}

	/**
	 * 获取会生成的entity类的名字
	 * 
	 * @param scanPackage
	 * @return string
	 */
	public String getEntity(String scanPackage) {
		List<String> classNames = getClassName(scanPackage);
		StringBuffer entityBuffer = new StringBuffer();
		for (String className : classNames) {
			// System.out.println(className);
			// 处理生成baseService中的mapper全局变量
			int lastIndexOf = className.lastIndexOf(".");
			String mapperName = className.substring(lastIndexOf + 1);// 得到生成的mapper的名字
			if (!mapperName.contains(mapperStr))// 不包含Mapper不向下执行
				continue;
			String entityName = mapperName.split(mapperStr)[0];// 得到entity类的名字
			if (StrUtil.isEmpty(entityName))
				continue;
			entityBuffer.append(entityName).append(",");
		}
		return entityBuffer.toString();
	}
	
	/**
	 * 指定扫描的mapper包下的类进行生成，springBoot框架,生成文件的字符编码UTF-8,首次生成
	 * 
	 * @param filePath
	 *            文件硬盘路径（到src文件夹就好了，不用到具体的java包处,<br>
	 *            如果是interlli工具则路径应为：“E:\\project\\interlli\\ideaProjects\\nobodyService\\src\\main\\java”<br>
	 *            如果是eclipse工具则路径应为：“E:\\project\\desk\\nobodyService\\src”）
	 * @param scanPackage
	 *            扫描的mapper包名
	 */
	public void createClass(String filePath, String scanPackage) {
		createClass(filePath, scanPackage, CreateSBF.encode, null, null, null,true);
	}

	/**
	 * 指定扫描的mapper包下的类进行生成，springBoot框架
	 * 
	 * @param filePath
	 *            文件硬盘路径（到src文件夹就好了，不用到具体的java包处,<br>
	 *            如果是interlli工具则路径应为：“E:\\project\\interlli\\ideaProjects\\nobodyService\\src\\main\\java”<br>
	 *            如果是eclipse工具则路径应为：“E:\\project\\desk\\nobodyService\\src”）
	 * @param scanPackage
	 *            扫描的mapper包名
	 * @param encode
	 *            生成文件的字符编码（默认UTF-8）
	 * @param first
	 *            是不是首次生成
	 */
	public void createClass(String filePath, String scanPackage, String encode,boolean first) {
		createClass(filePath, scanPackage, encode, null, null, null,first);
	}

	/**
	 * 指定扫描的mapper包下的类进行过虑生成springBoot框架，没有要过虑的设为null
	 * 
	 * @param filePath
	 *            文件硬盘路径（到src文件夹就好了，不用到具体的java包处,<br>
	 *            如果是interlli工具则路径应为：“E:\\project\\interlli\\ideaProjects\\nobodyService\\src\\main\\java”<br>
	 *            如果是eclipse工具则路径应为：“E:\\project\\desk\\nobodyService\\src”）
	 * @param scanPackage
	 *            扫描的mapper包名
	 * @param encode
	 *            生成文件的字符编码（默认UTF-8）
	 * @param ingoredService
	 *            过虑的service不用生成
	 * @param ingoredController
	 *            过虑的controller不用生成
	 * @param ingoredMapper
	 *            过虑的mapper不用处理
	 * @param first
	           是不是首次生成
	 */
	public void createClass(String filePath, String scanPackage, String encode, String ingoredService[],
			String ingoredController[], String ingoredMapper[],boolean first) {
		if (StrUtil.isEmptyNull(filePath))
			System.out.println("生成文件硬盘路径不能为空");
		CreateSBF.filePath = filePath + File.separatorChar;
		if (encode != null)
			CreateSBF.encode = encode;
		if (ingoredService != null)
			CreateSBF.ingoredService = ingoredService;
		if (ingoredController != null)
			CreateSBF.ingoredController = ingoredController;
		if (ingoredMapper != null)
			CreateSBF.ingoredMapper = ingoredMapper;
		List<String> classNames = getClassName(scanPackage);
		StringBuffer baseServersParamBuffer = new StringBuffer();
		StringBuffer baseControllerParamBuffer = new StringBuffer();
		for (String className : classNames) {// 遍历生成java类
			// System.out.println(className);

			// 处理生成baseService中的mapper全局变量
			int lastIndexOf = className.lastIndexOf(".");
			String mapperName = className.substring(lastIndexOf + 1);// 得到生成的mapper的名字
			String nameFirst = className.substring(lastIndexOf + 1, lastIndexOf + 2).toLowerCase();
			String nameLast = className.substring(lastIndexOf + 2);
			String baseServiceGlobalParam = StrUtil.stringBuilder("\t@Autowired\n", "\tprotected ", mapperName, " ",
					nameFirst + nameLast, ";\n");
			if (!mapperName.contains(mapperStr))// 不包含Mapper不向下执行
				continue;
			String entityName = mapperName.split(mapperStr)[0];// 得到entity类的名字
			if (StrUtil.isEmpty(entityName))
				continue;
			baseServersParamBuffer.append(baseServiceGlobalParam);
			// 处理生成baseController中的service全局变量
			String conParam = entityName + Service;
			nameFirst = conParam.substring(0, 1).toLowerCase();
			nameLast = conParam.substring(1);
			String baseControllerGlobalParam = StrUtil.stringBuilder("\t@Autowired\n", "\tprotected ", conParam, " ",
					nameFirst + nameLast, ";\n");
			baseControllerParamBuffer.append(baseControllerGlobalParam);
			// 生成其它
			reConfMapper(mapperName, entityName, first);
			createService(CreateSBF.entityPackageDot, entityName);
			createController(CreateSBF.entityPackageDot, entityName);
			// break;
			// System.out.println(baseGlobalParam);
			// System.out.println(mapperName);
			// System.out.println(baseControllerGlobalParam);
			// System.out.println(nameFirst);
			// System.out.println(nameLast);
		}
		creadBaseMapper();
		createBaseService(baseServersParamBuffer.toString());
		createBaseController(baseControllerParamBuffer.toString());
		createDatasource();
	}

	/**
	 * 生成基础的mapper(MapperBase)
	 */
	private void creadBaseMapper() {
		String baseMapperTemplate = "package %s;\n" + "\n" + "import org.apache.ibatis.annotations.Param;\n" + "\n"
				+ "import java.util.List;\n" + "\n" + "public interface MapperBase <T,TM> {\n" + "\n"
				+ "    long countByExample(TM example);\n" + "\n" + "    int deleteByExample(TM example);\n" + "\n"
				+ "    int deleteByPrimaryKey(Integer id);\n" + "\n" + "    int insert(T record);\n" + "\n"
				+ "    int insertSelective(T record);\n" + "\n" + "    List<T> selectByExample(TM example);\n" + "\n"
				+ "    T selectByPrimaryKey(Integer id);\n" + "\n"
				+ "    int updateByExampleSelective(@Param(\"record\") T record, @Param(\"example\") TM example);\n"
				+ "\n" + "    int updateByExample(@Param(\"record\") T record, @Param(\"example\") TM example);\n"
				+ "\n" + "    int updateByPrimaryKeySelective(T record);\n" + "\n"
				+ "    int updateByPrimaryKey(T record);\n" + "}";
		String baseMapperContent = String.format(baseMapperTemplate, mapperPackage);
		String interPath = StrUtil.stringBuilder(mapperPackageDot.replace(".", "/"), baseMapper, java);
		// System.out.println(interPath);
		// System.out.println(classPath);
		File classFile = new File(filePath + interPath);
		// System.out.println(classFile.getPath());
		if (!classFile.exists())
			FileUtil.saveFile4Str(baseMapperContent, classFile.getPath(), FileUtil.UTF8, false);
	}

	/**
	 * 重构mapper
	 *
	 * @param mapperName
	 * @param entityName
	 * @param first
	 */
	private void reConfMapper(String mapperName, String entityName, boolean first) {
		if (strArrContains(ingoredMapper, entityName))// 包含过虑的则不处理
			return;
		String classPath = StrUtil.stringBuilder(mapperPackageDot.replace(".", "/"), mapperName, java);
		File file = new File(filePath + classPath);
		System.out.println(file.getPath());
		if (!first && file.exists())
			return;
		// String content = FileUtil.readFile2str(file, encode);
		// String[] split = content.split("interface");
		String extendStr = "package {0};\n" + "\n" + "import {1};\n" + "import {2};\n" + "\n"
				+ "public interface {3} extends {6} <{4},{5}>'{'\n\n'}'";
		String calssContent = MessageFormat.format(extendStr, mapperPackage, entityPackageDot + entityName,
				entityPackageDot + entityName + Example, mapperName, entityName, entityName + Example, baseMapper);
		// System.out.println(calssContent);
		FileUtil.saveFile4Str(calssContent, file.getPath(), encode, true);
	}

	/**
	 * 生成基础service(baseService)类
	 *
	 * @param baseGlobalParam
	 */
	private void createBaseService(String baseGlobalParam) {
		String baseClassTemplate = "package %s;\n" + "\n" + "import %s;\n"
				+ "import %s;\n" + "import org.slf4j.Logger;\n"
				+ "import org.slf4j.LoggerFactory;\n"
				+ "import org.springframework.beans.factory.annotation.Autowired;\n" + "\n"
				+ "import javax.annotation.PostConstruct;\n" + "import java.lang.reflect.Field;\n"
				+ "import java.lang.reflect.ParameterizedType;\n" + "import java.util.List;\n" + "\n"
				+ "public class %s<T, TM> implements BaseService<T, TM> {\n" + "\n"
				+ "    private Class clazzT; // clazz中存储了当前操作的类型，即泛型T\n"
				+ "    private Class clazzTM; // clazz中存储了当前操作的类型，即泛型T\n" + "    protected Logger logger;\n"
				+ "    protected String error;\n" + "    // @Resource //baseDao是泛型，不能够注入只能通过上面的init方法赋值\n"
				+ "    private %s mapperBase;// = new BaseDaoImpl<T>();\n" + "\n" + "    public %s() {\n"
				+ "        // System.out.println(\"this代表BaseServiceImpl的是当前调用构造方法的对象\" + this);\n"
				+ "        // System.out.println(\"BaseServiceImpl获取当前this对象的父类信息\" +\n"
				+ "        // this.getClass().getSuperclass());\n"
				+ "        // System.out.println(\"BaseServiceImpl获取当前this对象的父类信息(包括泛型信息)\" +\n"
				+ "        // this.getClass().getGenericSuperclass());\n" + "        // 拿到泛型的参数类型\n"
				+ "        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();\n"
				+ "        clazzT = (Class) type.getActualTypeArguments()[0];\n"
				+ "        clazzTM = (Class) type.getActualTypeArguments()[1];\n"
				+ "//         System.out.println(\"clazzT = \" + clazzT);\n"
				+ "//        System.out.println(\"clazzTM = \" + clazzTM);\n" + "    }\n" + "\n"
				+ "    @PostConstruct\n" + "    public void init() {\n"
				+ "//         根据clazz的类型，把不同的dao对象复制给baseDao对象\n"
				+ "        String clazzName = clazzT.getSimpleName();\n"
				+ "        String clazzToName = clazzName.substring(0, 1).toLowerCase() + clazzName.substring(1);\n"
				+ "        String clazzMapper = clazzToName + \"Mapper\"; // 例如Account==>accountDao\n"
				+ "        System.out.println(\"BaseServiceImpl calssMapper = \" + clazzMapper);\n"
				+ "        logger = LoggerFactory.getLogger(clazzToName + \"ServiceImpl\");//getLogger(clazzToName + \"ServiceImpl\");\n"
				+ "        try {\n" + "//             Field clazzField = this.getClass().getField(clazzMapper);\n"
				+ "//             Field baseField = this.getClass().getField(\"mapperBase\");\n"
				+ "            Field clazzField = this.getClass().getSuperclass().getDeclaredField(clazzMapper);\n"
				+ "            Field baseField = this.getClass().getSuperclass().getDeclaredField(\"mapperBase\");\n"
				+ "//            Object o = ;\n"
				+ "            baseField.set(this, clazzField.get(this)); // mapperBase就有值了\n"
				+ "//             System.out.println(\"clazzField.get(this) = \" +\n"
				+ "//             clazzField.get(this).getClass().getName()+ \" mapperBase =\" + mapperBase);\n"
				+ "        } catch (Exception e) {\n" + "            logger.error(\"init error\", e);\n"
				+ "            throw new RuntimeException(e);\n" + "        }\n" + "    }\n" + "\n" + "   %s\n" + "\n"
				+ "\n" + "    @Override\n" + "    public long countByExample(TM example) {\n"
				+ "        return mapperBase.countByExample(example);\n" + "    }\n" + "\n" + "    @Override\n"
				+ "    public int deleteByExample(TM example) {\n"
				+ "        return mapperBase.deleteByExample(example);\n" + "    }\n" + "\n" + "    @Override\n"
				+ "    public int deleteByPrimaryKey(Integer id) {\n"
				+ "        return mapperBase.deleteByPrimaryKey(id);\n" + "    }\n" + "\n" + "    @Override\n"
				+ "    public int insert(T record) {\n" + "        return mapperBase.insert(record);\n" + "    }\n"
				+ "\n" + "    @Override\n" + "    public int insertSelective(T record) {\n"
				+ "        return mapperBase.insertSelective(record);\n" + "    }\n" + "\n" + "    @Override\n"
				+ "    public List<T> selectByExample(TM example) {\n"
				+ "        return mapperBase.selectByExample(example);\n" + "    }\n" + "\n" + "    @Override\n"
				+ "    public T selectByPrimaryKey(Integer id) {\n"
				+ "        return (T) mapperBase.selectByPrimaryKey(id);\n" + "    }\n" + "\n" + "    @Override\n"
				+ "    public int updateByExampleSelective(T record, TM example) {\n"
				+ "        return mapperBase.updateByExampleSelective(record, example);\n" + "    }\n" + "\n"
				+ "    @Override\n" + "    public int updateByExample(T record, TM example) {\n"
				+ "        return mapperBase.updateByExample(record, example);\n" + "    }\n" + "\n" + "    @Override\n"
				+ "    public int updateByPrimaryKeySelective(T record) {\n"
				+ "        return mapperBase.updateByPrimaryKeySelective(record);\n" + "    }\n" + "\n"
				+ "    @Override\n" + "    public int updateByPrimaryKey(T record) {\n"
				+ "        return mapperBase.updateByPrimaryKey(record);\n" + "    }\n" + "}\n";

		String baseServiceInterface = "package %s;\n" + "\n" + "import org.apache.ibatis.annotations.Param;\n" + "\n"
				+ "import java.util.List;\n" + "\n" + "public interface %s<T,TM> {\n" + "\n"
				+ "    long countByExample(TM example);\n" + "\n" + "    int deleteByExample(TM example);\n" + "\n"
				+ "    int deleteByPrimaryKey(Integer id);\n" + "\n" + "    int insert(T record);\n" + "\n"
				+ "    int insertSelective(T record);\n" + "\n" + "    List<T> selectByExample(TM example);\n" + "\n"
				+ "    T selectByPrimaryKey(Integer id);\n" + "\n"
				+ "    int updateByExampleSelective(@Param(\"record\") T record, @Param(\"example\") TM example);\n"
				+ "\n" + "    int updateByExample(@Param(\"record\") T record, @Param(\"example\") TM example);\n"
				+ "\n" + "    int updateByPrimaryKeySelective(T record);\n" + "\n"
				+ "    int updateByPrimaryKey(T record);\n" + "}\n";
		String baseServiceImpl = baseService + Impl;
		String baseClassServiceContent = String.format(baseClassTemplate, serversImplPackage, mapperPackageDot + "*",
				entityServersPackageDot + baseService, baseServiceImpl, baseMapper, baseServiceImpl, baseGlobalParam);
		String baseInterfaceServiceContent = String.format(baseServiceInterface, entityServersPackage, baseService);
		String interPath = StrUtil.stringBuilder(entityServersPackageDot.replace(".", "/"), baseService, java);
		String classPath = StrUtil.stringBuilder((serversImplPackage + ".").replace(".", "/"), baseServiceImpl, java);
		// System.out.println(interPath);
		// System.out.println(classPath);
		File classFile = new File(filePath + classPath);
		// System.out.println(classFile.getPath());
		if (!classFile.exists())
			FileUtil.saveFile4Str(baseClassServiceContent, classFile.getPath(), encode, false);
		File interFile = new File(filePath + interPath);
		System.out.println(interFile.getPath());
		if (!interFile.exists())
			FileUtil.saveFile4Str(baseInterfaceServiceContent, interFile.getPath(), encode, false);
		// System.out.println(baseClassServiceContent);
		// System.out.println(baseInterfaceServiceContent);

	}

	/**
	 * 生成service层
	 *
	 * @param entityPackage
	 * @param entityName
	 */
	private void createService(String entityPackage, String entityName) {
		if (strArrContains(ingoredService, entityName))// 包含过虑的则不处理
			return;
		String entityServiceInterface = "package {0};\n" + "import {1};\n" + "import {2};\n"
				+ "public interface {3} extends BaseService<{4},{5}> '{'\n\n'}'";

		String entityServiceClass = "package {0};\n" + "\n" + "import {1};\n" + "import {2};\n"
				+ "import com.djotimes.nobodyService.service.{8};\n"
				+ "import org.springframework.stereotype.Service;\n" + "\n" + "@Service(\"{3}\")\n"
				+ "public class {4} extends BaseServiceImpl<{5},{6}> implements {7} '{'\n\n'}'";

		String example = entityName + Example;
		String serviceName = entityName + Service;
		String serviceImplName = serviceName + Impl;
		String entityPackage_entityName = entityPackage + entityName;
		String entityPackage_example = entityPackage + example;

		// System.out.println(entityName);
		String interfaceContent = MessageFormat.format(entityServiceInterface, entityServersPackage,
				entityPackage_entityName, entityPackage_example, serviceName, entityName, example);

		String classContent = MessageFormat.format(entityServiceClass, serversImplPackage, entityPackage_entityName,
				entityPackage_example, StrUtil.strFirstToLow(serviceName,true), serviceImplName, entityName, example, serviceName, serviceName);
		// System.out.println(interfaceContent);
		// System.out.println(classContent);
		String interPath = StrUtil.stringBuilder(entityServersPackageDot.replace(".", "/"), serviceName, java);
		String classPath = StrUtil.stringBuilder((serversImplPackage + ".").replace(".", "/"), serviceImplName, java);
		File classFile = new File(filePath + classPath);
		System.out.println(classFile.getPath());
		if (!classFile.exists())
			FileUtil.saveFile4Str(classContent, classFile.getPath(), encode, false);
		File interFile = new File(filePath + interPath);
		System.out.println(interFile.getPath());
		if (!interFile.exists())
			FileUtil.saveFile4Str(interfaceContent, interFile.getPath(), encode, false);

	}

	/**
	 * 生成controller层
	 *
	 * @param entityPackage
	 * @param entityName
	 */
	private void createController(String entityPackage, String entityName) {
		if (strArrContains(ingoredController, entityName))// 包含过虑的则不处理
			return;
		String controllerContent = "package {0};\n" + // com.djotimes.nobodyService.controller
				"\n" + "import {1};\n" + // com.djotimes.nobodyService.controller.commons.BaseController
				"import {2};\n" + // com.djotimes.nobodyService.entity.DjAdmin
				"import org.springframework.stereotype.Controller;\n" + "\n" + "@Controller\n"
				+ "public class {3} extends BaseController<{4}> '{'\n\n'}'";

		String entityPackage_entityName = entityPackage + entityName;
		String contrName = entityName + controller;

		String calssContent = MessageFormat.format(controllerContent, entityControllerPackage,
				controllerBaseDot + baseController, entityPackage_entityName, contrName, entityName);
		// System.out.println(calssContent);
		String classPath = StrUtil.stringBuilder(entityControllerPackageDot.replace(".", "/"), contrName, java);
		File file = new File(filePath + classPath);
		// System.out.println(file.getPath());
		if (file.exists())
			return;
		FileUtil.saveFile4Str(calssContent, file.getPath(), encode, false);
	}

	/**
	 * 生成baseController
	 *
	 * @param param
	 */
	private void createBaseController(String param) {
		String baseTemplate = "package %s;\n" + // com.djotimes.nobodyService.controller.commons;
				"\n" + "import com.atgeretg.util.json.ali.JacksonUtil;\n" + "import %s;\n" + // com.djotimes.nobodyService.service.*
				"import org.springframework.beans.factory.annotation.Autowired;\n" + "\n"
				+ "import java.util.HashMap;\n" + "import java.util.List;\n" + "import java.util.Map;\n" + "\n"
				+ "public class BaseController<T> {\n" + "    \n"
				+ "    \n" + "%s\n" + "    \n"
				+ "    // 用来装有将要被打包成json格式返回给前台的数据，下面要实现get方法\n" + "    protected List<T> jsonList = null;\n"
				+ "    protected Map<String, Object> jsonMap;\n" + "\n"
				+ "    protected String statusMap(boolean success, String msg, Object data) {\n"
				+ "        jsonMap = new HashMap<String, Object>();\n" + "        int intSuc = success ? 1 : 0;\n"
				+ "        jsonMap.put(\"success\", intSuc);\n" + "        if(msg != null)\n"
				+ "            jsonMap.put(\"msg\", msg);\n" + "        if(data != null)\n"
				+ "            jsonMap.put(\"data\", data);\n" + "        return JacksonUtil.toJson(jsonMap);\n"
				+ "    }\n" + "}";

		String classContent = String.format(baseTemplate, controllerBase,
				entityServersPackageDot + "*", param);
		// System.out.println(classContent);
		String classPath = StrUtil.stringBuilder(controllerBaseDot.replace(".", "/"), baseController, java);
		File file = new File(filePath + classPath);
		System.out.println(file.getPath());
		if (file.exists())
			return;
		FileUtil.saveFile4Str(classContent, file.getPath(), encode, false);
	}

	/**
	 * 生成database有关类
	 */
	private void createDatasource() {

		String myBatisCongTemplate = "package %s;\r\n" + "\r\n" + "import com.atomikos.jdbc.AtomikosDataSourceBean;\r\n"
				+ "import %s;\r\n" + "import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;\r\n"
				+ "import org.apache.ibatis.session.SqlSessionFactory;\r\n"
				+ "import org.mybatis.spring.SqlSessionFactoryBean;\r\n"
				+ "import org.mybatis.spring.SqlSessionTemplate;\r\n"
				+ "import org.mybatis.spring.annotation.MapperScan;\r\n"
				+ "import org.springframework.beans.factory.annotation.Qualifier;\r\n"
				+ "import org.springframework.context.annotation.Bean;\r\n"
				+ "import org.springframework.context.annotation.Configuration;\r\n"
				+ "import org.springframework.context.annotation.Primary;\r\n" + "\r\n"
				+ "import javax.sql.DataSource;\r\n" + "import java.sql.SQLException;\r\n" + "\r\n"
				+ "@Configuration\r\n"
				+ "@MapperScan(basePackages = \"%s\", sqlSessionTemplateRef = \"sqlSessionTemplate1\")\r\n"
				+ "public class %s {\r\n" + "    @Bean(name = \"dataSourceOne\")\r\n" + "    @Primary\r\n"
				+ "    public DataSource dataSource1(%s dbConfigMain) throws SQLException {\r\n"
				+ "        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();\r\n"
				+ "        mysqlXADataSource.setURL(dbConfigMain.getUrl());\r\n"
				+ "        mysqlXADataSource.setPassword(dbConfigMain.getPassword());\r\n"
				+ "        mysqlXADataSource.setUser(dbConfigMain.getUsername());\r\n"
				+ "        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);\r\n"
				+ "//        mysqlXADataSource.setUser(dbConfig1.getUrl());\r\n" + "\r\n"
				+ "        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();\r\n"
				+ "        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);\r\n"
				+ "        atomikosDataSourceBean.setUniqueResourceName(\"dataSourceOne\");\r\n" + "\r\n"
				+ "        atomikosDataSourceBean.setMinPoolSize(dbConfigMain.getMinPoolSize());\r\n"
				+ "        atomikosDataSourceBean.setMaxPoolSize(dbConfigMain.getMaxPoolSize());\r\n"
				+ "        atomikosDataSourceBean.setMaxLifetime(dbConfigMain.getMaxLifetime());\r\n"
				+ "        atomikosDataSourceBean.setBorrowConnectionTimeout(dbConfigMain.getBorrowConnectionTimeout());\r\n"
				+ "        atomikosDataSourceBean.setLoginTimeout(dbConfigMain.getLoginTimeout());\r\n"
				+ "        atomikosDataSourceBean.setMaintenanceInterval(dbConfigMain.getMaintenanceInterval());\r\n"
				+ "        atomikosDataSourceBean.setMaxIdleTime(dbConfigMain.getMaxIdleTime());\r\n"
				+ "        atomikosDataSourceBean.setTestQuery(dbConfigMain.getTestQuery());\r\n" + "\r\n"
				+ "        return atomikosDataSourceBean;\r\n" + "\r\n" + "    }\r\n" + "\r\n"
				+ "    @Bean(name = \"sqlSessionFactory1\")\r\n"
				+ "    public SqlSessionFactory sqlSessionFactoryOne(@Qualifier(\"dataSourceOne\") DataSource dataSource) throws Exception {\r\n"
				+ "        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();\r\n"
				+ "        bean.setDataSource(dataSource);\r\n" + "        return bean.getObject();\r\n" + "    }\r\n"
				+ "\r\n" + "    @Bean(name = \"sqlSessionTemplate1\")\r\n"
				+ "    public SqlSessionTemplate sqlSessionTemplate(@Qualifier(\"sqlSessionFactory1\") SqlSessionFactory factory) {\r\n"
				+ "        return new SqlSessionTemplate(factory);\r\n" + "    }\r\n" + "}\r\n" + "";

		String dbConfTemplate = "package %s;\r\n" + "\r\n"
				+ "import org.springframework.boot.context.properties.ConfigurationProperties;\r\n" + "\r\n"
				+ "@ConfigurationProperties(prefix = \"mysql.datasource.main\")\r\n" + "public class %s {\r\n"
				+ "    private String url;\r\n" + "    private String username;\r\n"
				+ "    private String password;\r\n" + "    private int minPoolSize;\r\n"
				+ "    private int maxPoolSize;\r\n" + "    private int maxLifetime;\r\n"
				+ "    private int borrowConnectionTimeout;\r\n" + "    private int loginTimeout;\r\n"
				+ "    private int maintenanceInterval;\r\n" + "    private int maxIdleTime;\r\n"
				+ "    private String testQuery;\r\n" + "\r\n" + "    public String getUrl() {\r\n"
				+ "        return url;\r\n" + "    }\r\n" + "\r\n" + "    public void setUrl(String url) {\r\n"
				+ "        this.url = url;\r\n" + "    }\r\n" + "\r\n" + "    public String getUsername() {\r\n"
				+ "        return username;\r\n" + "    }\r\n" + "\r\n"
				+ "    public void setUsername(String username) {\r\n" + "        this.username = username;\r\n"
				+ "    }\r\n" + "\r\n" + "    public String getPassword() {\r\n" + "        return password;\r\n"
				+ "    }\r\n" + "\r\n" + "    public void setPassword(String password) {\r\n"
				+ "        this.password = password;\r\n" + "    }\r\n" + "\r\n"
				+ "    public int getMinPoolSize() {\r\n" + "        return minPoolSize;\r\n" + "    }\r\n" + "\r\n"
				+ "    public void setMinPoolSize(int minPoolSize) {\r\n"
				+ "        this.minPoolSize = minPoolSize;\r\n" + "    }\r\n" + "\r\n"
				+ "    public int getMaxPoolSize() {\r\n" + "        return maxPoolSize;\r\n" + "    }\r\n" + "\r\n"
				+ "    public void setMaxPoolSize(int maxPoolSize) {\r\n"
				+ "        this.maxPoolSize = maxPoolSize;\r\n" + "    }\r\n" + "\r\n"
				+ "    public int getMaxLifetime() {\r\n" + "        return maxLifetime;\r\n" + "    }\r\n" + "\r\n"
				+ "    public void setMaxLifetime(int maxLifetime) {\r\n"
				+ "        this.maxLifetime = maxLifetime;\r\n" + "    }\r\n" + "\r\n"
				+ "    public int getBorrowConnectionTimeout() {\r\n" + "        return borrowConnectionTimeout;\r\n"
				+ "    }\r\n" + "\r\n" + "    public void setBorrowConnectionTimeout(int borrowConnectionTimeout) {\r\n"
				+ "        this.borrowConnectionTimeout = borrowConnectionTimeout;\r\n" + "    }\r\n" + "\r\n"
				+ "    public int getLoginTimeout() {\r\n" + "        return loginTimeout;\r\n" + "    }\r\n" + "\r\n"
				+ "    public void setLoginTimeout(int loginTimeout) {\r\n"
				+ "        this.loginTimeout = loginTimeout;\r\n" + "    }\r\n" + "\r\n"
				+ "    public int getMaintenanceInterval() {\r\n" + "        return maintenanceInterval;\r\n"
				+ "    }\r\n" + "\r\n" + "    public void setMaintenanceInterval(int maintenanceInterval) {\r\n"
				+ "        this.maintenanceInterval = maintenanceInterval;\r\n" + "    }\r\n" + "\r\n"
				+ "    public int getMaxIdleTime() {\r\n" + "        return maxIdleTime;\r\n" + "    }\r\n" + "\r\n"
				+ "    public void setMaxIdleTime(int maxIdleTime) {\r\n"
				+ "        this.maxIdleTime = maxIdleTime;\r\n" + "    }\r\n" + "\r\n"
				+ "    public String getTestQuery() {\r\n" + "        return testQuery;\r\n" + "    }\r\n" + "\r\n"
				+ "    public void setTestQuery(String testQuery) {\r\n" + "        this.testQuery = testQuery;\r\n"
				+ "    }\r\n" + "}\r\n";
		String db = dataSourcePackageDot + "DB";
		String dbClassContent = String.format(dbConfTemplate, db, DBConfigMain);
		// System.out.println(dbClassContent);
		String dbClassPath = StrUtil.stringBuilder((dataSourcePackageDot + "DB.").replace(".", "/"), DBConfigMain,
				java);
		File dbFile = new File(filePath + dbClassPath);
		// System.out.println(dbFile.getPath());
		if (!dbFile.exists())
			FileUtil.saveFile4Str(dbClassContent, dbFile.getPath(), encode, false);
		String myBitysClassContent = String.format(myBatisCongTemplate, dataSourcePackage, db + "." + DBConfigMain,
				mapperPackage, MyBatisConfigMain, DBConfigMain);
		// System.out.println(myBitysClassContent);
		String myBitysClassPath = StrUtil.stringBuilder(dataSourcePackageDot.replace(".", "/"), MyBatisConfigMain,
				java);
		File myFile = new File(filePath + myBitysClassPath);
		// System.out.println(myFile.getPath());
		if (!myFile.exists())
			FileUtil.saveFile4Str(myBitysClassContent, myFile.getPath(), encode, false);

	}

	/**
	 * 数组中是否包含某字符，包含返回true
	 *
	 * @param arr
	 * @param targetValue
	 * @return true | false
	 */
	private boolean strArrContains(String[] arr, String targetValue) {
		return StrUtil.strArrContains(arr, targetValue);
	}

	/**
	 * 最后一个字符串要不要点
	 *
	 * @param string
	 * @param need
	 *            要不要点 return 传入的字符串，加上点或去除点
	 */
	private static String lastStrIsDot(String string, boolean need) {
		boolean have = (".".equals(string.substring(string.length() - 1)));
		if (need) {// 需要点
			if (have)// 有点
				return string;
			return string + ".";
		} else {
			if (have)// 有点
				return string.substring(0, string.length() - 1);
			return string;
		}

	}
	
	

	private List<String> getClassName(String packageName) {
		String filePath = ClassLoader.getSystemResource("").getPath() + packageName.replace(".", "/");
		List<String> fileNames = getClassName(filePath, null);
		return fileNames;
	}

	private List<String> getClassName(String filePath, List<String> className) {
		List<String> myClassName = new ArrayList<String>();
		File file = new File(filePath);
		File[] childFiles = file.listFiles();
		for (File childFile : childFiles) {
			if (childFile.isDirectory()) {
				myClassName.addAll(getClassName(childFile.getPath(), myClassName));
			} else {
				String childFilePath = childFile.getPath();
				if (childFilePath == null
						|| !(childFilePath.substring(childFilePath.lastIndexOf(".")).equals(".class")))
					continue;
				// System.out.println(childFilePath);
				childFilePath = childFilePath.substring(childFilePath.indexOf("\\classes") + 9,
						childFilePath.lastIndexOf("."));
				childFilePath = childFilePath.replace("\\", ".");
				myClassName.add(childFilePath);
			}
		}

		return myClassName;
	}

}
