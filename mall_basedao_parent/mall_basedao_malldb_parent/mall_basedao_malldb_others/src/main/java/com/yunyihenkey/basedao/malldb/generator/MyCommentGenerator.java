package com.yunyihenkey.basedao.malldb.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author josnow
 * @date 2017年10月26日 上午11:41:35
 * @version 1.0.0
 * @desc 实体属性注释
 */
public class MyCommentGenerator extends DefaultCommentGenerator {
	private static 	Map<String, String> map =  new HashMap<String, String>();

	//@Value("${MyCommentGenerator.pathname}")
	private static 	String pathName = "E:\\v1.0.0\\mall_parent\\mall_basedao_parent\\mall_basedao_malldb_parent\\mall_basedao_malldb_others\\src\\main\\java\\com\\yunyihenkey\\basedao\\malldb\\basevoEnum";

	//@Value("${MyCommentGenerator.packagename}")
	private static 	String packageName = "com.yunyihenkey.basedao.malldb.basevoEnum";
	/*private static 	String allName = pathName +"\\src"+packageName.replaceAll(".","/");*/
	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (introspectedColumn.getRemarks() != null && !introspectedColumn.getRemarks().equals("")) {
			if (introspectedColumn.getRemarks().contains("#") && introspectedColumn.getRemarks().contains("|")) {
				map.put("jdbcType",introspectedColumn.getJdbcTypeName());
				map.put("context",introspectedColumn.getRemarks());
				map.put("tableName",introspectedTable.getFullyQualifiedTableNameAtRuntime());
				map.put("columnName",introspectedColumn.getActualColumnName());
				map.put("packageName",packageName +"."+ EnumUtil.camelName(introspectedTable.getFullyQualifiedTableNameAtRuntime()) );
				map.put("allName",pathName +"\\"+ EnumUtil.camelName(introspectedTable.getFullyQualifiedTableNameAtRuntime()));
				EnumUtil.getEnum(map);
				EnumUtil.writeFileString(map);
				//nihao.readFileString(map);
			}
			field.addJavaDocLine("/** " + introspectedColumn.getRemarks() + " */");
		}
	}

}
