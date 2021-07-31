package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class InserirConstasSteps {

    private WebDriver driver;

    @Dado("que estou acessando a aplicação")
    public void queEstouAcessandoAAplicação() {
        System.setProperty(
                "webdriver.gecko.driver",
                "/home/alencar/www/Selenium/geckodriver/geckodriver"
        );
        driver = new FirefoxDriver();
        driver.manage().window().setPosition(new Point(100,100));
        driver.manage().window().setSize(new Dimension(1200, 735));
        driver.get("https://seubarriga.wcaquino.me/logout");
    }

    @Quando("informo o usuário {string}")
    public void informoOUsuário(String email) {
        driver.findElement(By.id("email")).sendKeys(email);
    }

    @Quando("a senha {string}")
    public void aSenha(String senha) {
        driver.findElement(By.id("senha")).sendKeys(senha);
    }

    @Quando("seleciono entrar")
    public void selecionoEntrar() {
        driver.findElement(By.tagName("button")).click();
    }

    @Então("visualizo a página inicial")
    public void visualizoAPáginaInicial() {
        String titulo = driver.getTitle();
        String txtBemVindo = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();

        Assert.assertEquals("Seu Barriga - Home", titulo);
        Assert.assertEquals("Bem vindo, Teste Cucumber!", txtBemVindo);
    }

    @Quando("seleciono Contas")
    public void selecionoContas() {
        driver.findElement(By.className("dropdown")).click();
    }

    @Quando("seleciono Adicionar")
    public void selecionoAdicionar() {
        driver.findElement(By.linkText("Adicionar")).click();
    }

    @Quando("informo a conta {string}")
    public void informoAConta(String nomeConta) {
        driver.findElement(By.className("form-control")).sendKeys(nomeConta);
    }

    @Quando("seleciono Salvar")
    public void selecionoSalvar() {
        driver.findElement(By.tagName("button")).click();
    }

    @Então("a conta é inserida com sucesso")
    public void aContaÉInseridaComSucesso() {
        String alert = driver.findElement(By.className("alert")).getText();
        if ("Já existe uma conta com esse nome!".equals(alert)){
            selecionoContas();
            driver.findElement(By.linkText("Listar")).click();
            String nomeConta = driver.findElement(By.tagName("td")).getText();
            Assert.assertEquals("Conta de Teste", nomeConta);
        } else {
            Assert.assertEquals("Conta adicionada com sucesso!", alert);
        }
    }

    @Então("sou notificado que o nome da conta é obrigatório")
    public void souNotificadoQueONomeDaContaÉObrigatório() {
        String txtAlert = driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
        Assert.assertEquals("Informe o nome da conta", txtAlert);
    }

    @Então("sou notificado que já existe uma conta com esse nome")
    public void souNotificadoQueJáExisteUmaContaComEsseNome() {
        String txtAlert = driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
        Assert.assertEquals("Já existe uma conta com esse nome!", txtAlert);
    }

    @Before
    public void exemploBefore(){
        System.out.println("Começando cenário aqui!");
    }

    @After // after do Cucumber
    public void fecharBrowser(){
        driver.quit();
    }
}
