import org.antlr.v4.runtime.tree.ParseTree;

import nodes.ASTNode;

import org.antlr.v4.runtime.*;
import java.io.InputStream;
import java.io.FileInputStream;

public class Guqin {

    public static void main(String[] args) throws Exception {
        InputStream is = args.length > 0 ? new FileInputStream(args[0]) : System.in;

        CharStream input = CharStreams.fromStream(is);
        guqinLexer lexer = new guqinLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        try {
            guqinParser parser = new guqinParser(tokens);
            ParseTree tree = parser.prog();
            int numberOfSyntaxErrors = parser.getNumberOfSyntaxErrors();
            if (numberOfSyntaxErrors > 0) {
                System.out.println("Syntax Error.");
                System.exit(-1);
            }
            ASTVisitor AST = new ASTVisitor();
            ASTNode res = AST.visit(tree);
            try {
                res.check();
            } catch (Exception e) {
                System.out.println(e);
                System.exit(-1);
            }
            System.out.println("OK");
            System.exit(0);
        } catch (RecognitionException e) {
            System.out.println(e);
            System.exit(-1);
        }
    }
}