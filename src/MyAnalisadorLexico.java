import java.io.IOException;
import java.util.Vector;
 
public class MyAnalisadorLexico extends AnalisadorLexico {
    public MyAnalisadorLexico(String _nomeArquivoEntrada) {
	super(_nomeArquivoEntrada);
    }
    public void s0() {
	if(this.proxCaractereIs(DIGITOS)) {//OK
            leProxCaractere();
            s1();
	}
	else if(this.proxCaractere == IGUAL) {
            String aux = "=";
            leProxCaractere();
            s5(aux);
	}
        else if(this.proxCaractereIs(WHILE)){
                int i = 0;
                boolean flag = false;
                Vector aux = new Vector();
                aux.add("w");
                aux.add("h");
                aux.add("i");
                aux.add("l");
                aux.add("e");
                //leProxCaractere();
                s3(i,flag,aux);
        
        }
	else if(this.proxCaractere == EOF) {
		leProxCaractere();
		s6();
	}
	else if(this.proxCaractereIs(VAZIOS)) {  
		leProxCaractere();
		s0();
	}
        else if(this.proxCaractereIs(OP)) {
                String aux="";
                if(this.proxCaractereIs("+")) aux = "+";
                if(this.proxCaractereIs("-")) aux = "-";
                if(this.proxCaractereIs(">")) aux = ">";
                if(this.proxCaractereIs("<")) aux = "<";
                if(this.proxCaractereIs("=")) aux = "=";
                if(this.proxCaractereIs("!")) aux = "!";
                if(this.proxCaractereIs("&")) aux = "&";
                if(this.proxCaractereIs("|")) aux = "|";
                      
               
                leProxCaractere();
                s5(aux);
        }
        else if(this.proxCaractere == DOISPONTOS){        
                leProxCaractere();
                s7();
        }
        else if(this.proxCaractere == ACH){
                leProxCaractere();
                s8();
        }
        else if(this.proxCaractere == FCH){
                leProxCaractere();
                s9();
        }
        
        else if(this.proxCaractere == AP){
                leProxCaractere();
                s10();
        }
	
        else if(this.proxCaractere == FP){
                leProxCaractere();
                s11();
        }
        
        else if(this.proxCaractere == PT_VIRG){
                leProxCaractere();
                s12();
        }
        
        else if(this.proxCaractereIs(FOR)){
                int i = 0;
                boolean flag = false;
                Vector aux = new Vector();
                aux.add("f");
                aux.add("o");
                aux.add("r");
                //leProxCaractere();
                s13(i,flag,aux);
        }
        else if(this.proxCaractereIs(CASE)){
                int i = 0;
                boolean flag = false;
                Vector aux = new Vector();
                aux.add("c");
                aux.add("a");
                aux.add("s");
                aux.add("e");
                //leProxCaractere();
                s14(i,flag,aux);        
        }
        else if(this.proxCaractereIs(SWITCH)){
                int i = 0;
                boolean flag = false;
                Vector aux = new Vector();//vetor com o resto da palavra
                aux.add("s");
                aux.add("w");
                aux.add("i");
                aux.add("t");
                aux.add("c");
                aux.add("h");
                //leProxCaractere();
                s15(i,flag,aux);        
        }
        
        else if(this.proxCaractereIs(IF)){
                int i = 0;
                boolean flag = false;
                Vector aux = new Vector();
                aux.add("i");
                aux.add("f");
                //leProxCaractere();
                s16(i,flag,aux);
        }
        
        else if(this.proxCaractereIs(DO)){
                int i = 0;
                boolean flag = false;
                Vector aux = new Vector();
                aux.add("d");
                aux.add("o");
                //leProxCaractere();
                s17(i,flag,aux);
        }
        
        else if(this.proxCaractere == ACO){
                leProxCaractere();
                s18();
        }
        
        else if(this.proxCaractere == FCO){
                leProxCaractere();
                s19();
        }
        
        else if(this.proxCaractereIs(LETRAS)){
                leProxCaractere();
                s4();
        }
        else if(this.proxCaractere==ASPAS){
                leProxCaractere();
                s20();
        }
        
	else {
            throw new ErroLexico(this.proxCaractere,DIGITOS+LETRAS+VAZIOS+PT_VIRG);
	}
    }
    public void s1() {
	this.tokenReconhecido = Token.NUM;
	if(this.proxCaractereIs(DIGITOS)) {
            leProxCaractere();
            s1();
	}
    }
    public void s2(){
        this.tokenReconhecido = Token.ATRIB;
    }
    public void s3(int i,boolean flag,Vector aux){
        if(i < 5 ) flag = this.proxCaractereIs((String)aux.get(i));
        leProxCaractere();
        i++;  
        if(!flag) s4();
        if(this.proxCaractereIs(LETRAS)){
            s3(i,flag,aux);
        }
        else{
            if(i == 5 && flag)
                this.tokenReconhecido = Token.WHILE;
            else
                s4();
        }
    }
    public void s4(){
        this.tokenReconhecido = Token.IDENT;
        if(this.proxCaractereIs(LETRAS)){
            leProxCaractere();
            s4();
        }
    }
    public void s5(String flag){
        
        /*if(this.proxCaractereIs("=")){
            leProxCaractere();
            s5(flag);
        }*/
        if(this.proxCaractereIs("+") && flag=="+"){
            leProxCaractere();
            this.tokenReconhecido = Token.OP_UNARIOS;
        }/*else if (this.proxCaractereIs(DIGITOS+LETRAS) && flag=="+"){
            leProxCaractere();
            this.tokenReconhecido = Token.SINAL;
        }*/
        else if(this.proxCaractereIs("-") && flag=="-"){
            leProxCaractere();
            this.tokenReconhecido = Token.OP_UNARIOS;
        }
        else if(this.proxCaractereIs("=") && flag=="-"){
            leProxCaractere();
            s2();
        }
        else if(this.proxCaractereIs("=") && flag=="+"){
            leProxCaractere();
            s2();
        }
        else  if(this.proxCaractereIs("=") && flag=="<"){
            leProxCaractere();
            this.tokenReconhecido = Token.OP;
        }
        else  if(this.proxCaractereIs("=") && flag==">"){
            leProxCaractere();
            this.tokenReconhecido = Token.OP;
        }
        else if(this.proxCaractereIs("=") && flag=="!"){
            leProxCaractere();
            this.tokenReconhecido = Token.OP;
        } 
        else  if(this.proxCaractereIs("=") && flag=="="){
            leProxCaractere();
            this.tokenReconhecido = Token.OP;
        }
        else if(flag=="=") 
              s2();
        else if(this.proxCaractereIs("&") && flag=="&"){
            leProxCaractere();
            this.tokenReconhecido = Token.OP;
        }
        else if(flag=="&")
             this.tokenReconhecido = Token.OP_UNARIOS;
        else if(this.proxCaractereIs("|") && flag=="|"){
            leProxCaractere();
            this.tokenReconhecido = Token.OP;
        }else if (flag=="|")
                this.tokenReconhecido = Token.OP_UNARIOS;
        else 
            this.tokenReconhecido = Token.OP;
    }
    
    public void s7(){
        this.tokenReconhecido = Token.DOISPONTOS;
        leProxCaractere();
        //s7();
    }
    
    public void s8(){
        this.tokenReconhecido = Token.ACH;
    }
    
    public void s9(){
        this.tokenReconhecido = Token.FCH;
    }
    
    public void s10(){
        this.tokenReconhecido = Token.AP;
    }
    
    public void s11(){
        this.tokenReconhecido = Token.FP;
    }
       
    public void s12(){
        this.tokenReconhecido = Token.PT_VIRG;
    }
    
    public void s13(int i, boolean flag, Vector aux){
        if(i<3) flag = this.proxCaractereIs((String)aux.get(i));
        leProxCaractere();
        i++;
        if(!flag) s4();
        if(this.proxCaractereIs(LETRAS)){
            s13(i,flag, aux);
        }
        else{
            if(i==3 && flag)
                this.tokenReconhecido = Token.FOR;
            else
                s4();
        }
    }
    
    public void s14(int i,boolean flag, Vector aux){
        if(i<4) flag = this.proxCaractereIs((String)aux.get(i));
        leProxCaractere();
        i++;
        if(!flag) s4();
        if(this.proxCaractereIs(LETRAS)){
            s14(i,flag, aux);
        }
        else{
            if(i==4 && flag)
                this.tokenReconhecido = Token.CASE;
            else
                s4();
        }
    }

    public void s15(int i,boolean flag, Vector aux){
        if(i<6) flag = this.proxCaractereIs((String)aux.get(i));
        leProxCaractere();
        i++;
        if(!flag) s4();
        if(this.proxCaractereIs(LETRAS)){
            s15(i,flag, aux);
        }
        else{
            if(i==6 && flag)
                this.tokenReconhecido = Token.SWITCH;
            else
                s4();
        }
    }
    
    public void s16(int i,boolean flag, Vector aux){
        if(i<2) flag = this.proxCaractereIs((String)aux.get(i));
        leProxCaractere();
        i++;
        if(!flag) s4();
        if(this.proxCaractereIs(LETRAS)){
            s16(i,flag, aux);
        }
        else{
            if(i==2 && flag)
                this.tokenReconhecido = Token.IF;
            else
                s4();
        }
    }
    
    public void s17(int i,boolean flag, Vector aux){
        if(i<2) flag = this.proxCaractereIs((String)aux.get(i));
        leProxCaractere();
        i++;
        if(!flag) s4();
        if(this.proxCaractereIs(LETRAS)){
            s17(i,flag, aux);
        }
        else{
            if(i==2 && flag)
                this.tokenReconhecido = Token.DO;
            else
                s4();
        }
    }
    
    public void s18(){
         this.tokenReconhecido = Token.ACO;
    }
    
    public void s19(){
         this.tokenReconhecido = Token.FCO;
    }
    public void s20(){
         this.tokenReconhecido = Token.ASPAS;
    }
    
    public void s6() {
	this.tokenReconhecido = Token.EOF;
    }

}	
