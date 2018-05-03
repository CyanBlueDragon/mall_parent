package com.yunyihenkey.basedao.malldb.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * @author LiarYang
 * @date 2018/4/26 19:04
 */
public class EnumUtil {
    public static void writeFileString(Map<String, String> map) {
        String packageName = map.get("allName");
        String name = map.get("name");
        String context  = map.get("context");
        File file = new File(packageName);

        if(!file.isDirectory())
            file.mkdir();
        File fileDir = new File(file,name+".java");
        if(!fileDir.isFile()) {
            try {
                fileDir.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fw = new FileWriter(fileDir);
            fw.write(context);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


               /* map.put("jdbcType",introspectedColumn.getJdbcTypeName());
				map.put("context",introspectedColumn.getRemarks());
				map.put("tableName",introspectedTable.getFullyQualifiedTableNameAtRuntime());
				map.put("columnName",introspectedColumn.getActualColumnName());*/
    public static Map<String, String> getEnum(Map<String, String> map)  {
        String tType = "String";
        String changeText = "code.getValue().equals(value)";
        StringBuilder sb = new StringBuilder();
        String type  = getType(map.get("jdbcType"));
        if (type.equals("int")) {
            tType = "Integer";
            changeText =   "code.getValue()== value";
        }
        String enumName = camelName(map.get("columnName")) + "Enum";
        sb.append("package ")
                .append(map.get("packageName"))
                .append(";\n\n\n")
                .append("public enum ")
                .append(enumName)
                .append(" {\n\t\n").append("\t")
                .append(stringUtil(map.get("context"), type))
                .append("\n")
                .append("\tprivate ")
                .append(type).append(" value;\n")
                .append("\tprivate ").append("String text;").append("\n")
                .append("\t\n\tprivate ").append(enumName).append("(").append(type).append(" value, String text){\n").append("\t\tthis.value = value;\n" +
                "\t\tthis.text = text;\n\t}")
                .append("\n\t\n\tpublic ")
                .append(type).append(" getValue() {\n" +
                "\t\treturn value;\n" +
                "\t}\n").append("\t\n\tpublic void setValue(").append(type).append(" value) {\n" +
                "\t\tthis.value = value;\n" +
                "\t}").append("\n\t\n\tpublic String getText() {\n" +
                "\t\treturn text;\n" +
                "\t}\n" +
                "\n" +
                "\t\n\tpublic void setText(String text) {\n" +
                "\t\tthis.text = text;\n" +
                "\t}").append("\n\t\n\tpublic static ").append(enumName).append(" getByValue(").append(tType).append(" value) {\n" +
                "\t\tif (value == null) {\n" +
                "\t\t\treturn null;\n" +
                "\t\t}").append("\n\t\tfor (").append(enumName).append(" code : values()) {\n" + "\t\t\tif (").
                append(changeText).
                append(") {\n").
                append("\t\t\t\treturn code;\n").
                append("\t\t\t}\n").
                append("\t\t}\n").append("\t\treturn null;\n").append("\t}").append("\n}");

        map.put("context",sb.toString());
        map.put("name",enumName);


        return map;
    }






    //#用户类型#0,卖家;1,分销商



    public static String camelName(String name) {
    StringBuilder result = new StringBuilder();
    if(name == null|| name.isEmpty()) {
        return"";
    }else if (!name.contains("_")) {
        return name.substring(0,1).toUpperCase()+ name.substring(1);
    }
    String camels[] = name.split("_");
    for(String camel :  camels) {
        if(camel.isEmpty()) {
            continue;
        }
            result.append(camel.substring(0,1).toUpperCase());
            result.append(camel.substring(1).toLowerCase());
    }
    return result.toString();
}

    private static String getType(String a ){
        if (a.equals("INTEGER")){
            return "int";
        }
        return "String";
    }

    private static String stringUtil(String l,String type) {
        String a = l.trim();
        StringBuilder sb = new StringBuilder();
        String substring = a.substring(a.indexOf("#")+1, a.length());
        String[] split = substring.split(";");
        for (String b :split) {
            String[] split1 = b.split(",");
            if (split1.length == 2) {
                if (type.equals("int")) {
                    sb.append(b, b.indexOf("|") + 1, b.length())
                            .append("(")
                            .append(split1[0])
                            .append(",")
                            .append("\"")
                            .append(split1[1], 0, split1[1].indexOf("|"))
                            .append("\"")
                            .append("),\n\t");
                }
                else {
                    sb.append(b, b.indexOf("|") + 1, b.length())
                            .append("(\"")
                            .append(split1[0])
                            .append("\",")
                            .append("\"")
                            .append(split1[1], 0, split1[1].indexOf("|"))
                            .append("\"")
                            .append("),\n\t");

                }
                }else {
                try {
                    throw new Exception("注释格式错误!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        String s = sb.toString();

        return  s+";";
    }
}
