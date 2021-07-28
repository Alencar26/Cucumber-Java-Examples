package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "/home/alencar/www/Curso-Cucumber-Java/src/test/resources/alugar_filme.feature", // caminho dos arquivos feature
        glue = "steps", // caminho/ pacote dos steps codificados
        plugin = "pretty",
        // monochrome = false, // para não aplicar cor no console
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        dryRun = false, // serve para validar se o mapeamento está correto
        tags = "not @ignore" // esse parâmetro é para indicar qual cenário eu quero executar sozinho
)
public class Runner { }
