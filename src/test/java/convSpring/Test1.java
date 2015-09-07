package convSpring;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.xml.sax.InputSource;

import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.api.S2JJAXBModel;
import com.sun.tools.xjc.api.SchemaCompiler;
import com.sun.tools.xjc.api.XJC;

public class Test1 {

	public static void main(String[] args) throws IOException {
		// Configure sources & output
        String schemaPath = "src/main/resources/XSD/CBIDlyStmtReqLogMsg.00.01.02.xsd";
        String outputDirectory = "target/classes";

        // Setup schema compiler
        SchemaCompiler sc = XJC.createSchemaCompiler();
        sc.forcePackageName("com.xyz.schema.generated");

        // Setup SAX InputSource
        File schemaFile = new File(schemaPath);
        InputSource is = new InputSource(new FileInputStream(schemaFile));
        is.setSystemId(schemaFile.getAbsolutePath());

        // Parse & build
        sc.parseSchema(is);
        S2JJAXBModel model = sc.bind();
        JCodeModel jCodeModel = model.generateCode(null, null);
        jCodeModel.build(new File(outputDirectory));
        
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
//        javaCompiler.run(in, out, err, arguments)
	}
}
