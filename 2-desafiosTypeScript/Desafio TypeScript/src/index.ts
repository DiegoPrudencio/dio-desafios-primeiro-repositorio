
//Declaração de variáveis globais 

var catetoA = document.getElementById("idCatetoA") as HTMLInputElement;
var catetoB = document.getElementById("idCatetoB") as HTMLInputElement;
var hipotenusa = document.getElementById("idHipotenusa") as HTMLInputElement;

var image1 = document.getElementById("idTriangle1") as HTMLElement;
var image2 = document.getElementById("idTriangle2") as HTMLElement;
var image3 = document.getElementById("idTriangle3") as HTMLElement;
var imageError = document.getElementById("idErrorGif") as HTMLElement;
var divImage = document.getElementById("idDivImages") as HTMLDivElement;
var divImageError = document.getElementById("idDivImageError") as HTMLDivElement;

var outHidden = document.getElementById("idDivResult") as HTMLDivElement;
var outputErrorHidden = document.getElementById("idDivOutputError") as HTMLDivElement;

var out1 = document.getElementById("idOut1") as HTMLOutputElement;
var out2 = document.getElementById("idOut2") as HTMLOutputElement;
var out3 = document.getElementById("idOut3") as HTMLOutputElement;
var out4 = document.getElementById("idOut4") as HTMLOutputElement;
var out5 = document.getElementById("idOut5") as HTMLOutputElement;
var out6 = document.getElementById("idOut6") as HTMLOutputElement;
var out7 = document.getElementById("idOut7") as HTMLOutputElement;
var outputError = document.getElementById("idOutError") as HTMLOutputElement;


//Variáveis botões
var btnCalcular = document.getElementById("idBtnCalcular") as HTMLButtonElement;
var btnLimpar = document.getElementById("idBtnLimpar") as HTMLButtonElement;


/*
 * Função Teorema de Pitágoras
 * Descrição: 
 *  - Rotina de Inicialização e verificação das informações;
 *  - Inicializa variáveis globais e tela;
 *  - Cálculo do através dos dados da tela;
 * Parâmetros: none
 * Retorno: none
 */
btnCalcular.onclick = function () {



    //Condições para cálculo dos valores
    if (catetoA.value && catetoB.value && hipotenusa.value) {



        //Verifica se o valores 
        let verificarValor: boolean = verificarValores(Number(catetoA.value), Number(catetoB.value), Number(hipotenusa.value))
        console.log(verificarValor);

        if (verificarValor == false) {

            outputErrorHidden.hidden = false
            outHidden.hidden = true
            divImageError.hidden = false
            divImage.hidden = true
            outputError.value = "O comprimento da Hipotenusa não pode ser menor que a soma dos quadrados dos Catetos A e B."

        } else if (verificarValor == true) {
            //Chama função para calcular segmentos da hipotenusa
            let segmento1 = calcularSegmento1(Number(catetoA.value), Number(hipotenusa.value))
            let segmento2 = calcularSegmento2(segmento1, Number(hipotenusa.value))

            //Altura hipotenusa
            let altura = calcularAltura(segmento1, segmento2)

            //Chama funções para calculo dos ângulos
            let anguloAlpha = calcularAnguloAlpha(Number(catetoA.value), Number(hipotenusa.value))
            let anguloBeta = calcularAnguloBeta(anguloAlpha)

            //Área do triângulo
            let area = calcularArea(altura, Number(hipotenusa.value))

            //Chama função para escolha de figura
            let image = imageChoice(Number(catetoA.value), Number(catetoB.value))

            divImage.hidden = false
            outHidden.hidden = false
            outputErrorHidden.hidden = true
            out1.value = "Comprimento do Cateto A: " + catetoA.value
            out2.value = "Comprimento do Cateto B: " + catetoB.value
            out3.value = "Hipotenusa: " + hipotenusa.value
            out4.value = "Segmentos da Hipotenusa: 1º - " + segmento1.toFixed(1) + " e 2º - " + segmento2.toFixed(1)
            out5.value = "Altura: " + altura.toFixed(1)
            out6.value = "Área: " + area.toFixed(1) + " m²"
            out7.value = "Ângulos: Alpha (α) - " + anguloAlpha.toFixed(1) + "º e " + " Beta (β) - " + anguloBeta.toFixed(1) + "º"
        }
    } else if (catetoA.value && catetoB.value) {


        //Chama função para calcular Hipotenusa
        let hipotenusaTriangulo = calcularHipotenusa(Number(catetoA.value), Number(catetoB.value))

        //Chama função para calcular segmentos da hipotenusa
        let segmento1 = calcularSegmento1(Number(catetoA.value), hipotenusaTriangulo)
        let segmento2 = calcularSegmento2(segmento1, hipotenusaTriangulo)

        //Altura hipotenusa
        let altura = calcularAltura(segmento1, segmento2)

        //Chama funções para calculo dos ângulos
        let anguloAlpha = calcularAnguloAlpha(Number(catetoA.value), hipotenusaTriangulo)
        let anguloBeta = calcularAnguloBeta(anguloAlpha)

        //Área do triângulo
        let area = calcularArea(altura, hipotenusaTriangulo)

        //Chama função para escolha de figura
        let image = imageChoice(Number(catetoA.value), Number(catetoB.value))

        outHidden.hidden = false
        outputErrorHidden.hidden = true
        divImage.hidden = false
        out1.value = "Comprimento do Cateto A: " + catetoA.value
        out2.value = "Comprimento do Cateto B: " + catetoB.value
        out3.value = "Hipotenusa: " + hipotenusaTriangulo.toFixed(1)
        out4.value = "Segmentos da Hipotenusa: 1º - " + segmento1.toFixed(1) + " e 2º - " + segmento2.toFixed(1)
        out5.value = "Altura: " + altura.toFixed(1)
        out6.value = "Área: " + area.toFixed(1) + " m²"
        out7.value = "Ângulos: Alpha (α) - " + anguloAlpha.toFixed(1) + "º e " + " Beta (β) - " + anguloBeta.toFixed(1) + "º"

    } else if (catetoB.value && hipotenusa.value) {

        //Chama função para calcular Cateto A
        let catetoA: any = calcularCatetoA(Number(catetoB.value), Number(hipotenusa.value))
        let catetos: boolean = verificarCatetos(Number(catetoA), Number(catetoB.value), Number(hipotenusa.value))
        console.log(catetos);

        if (Number.isNaN(catetoA)) {

            outputErrorHidden.hidden = false
            outHidden.hidden = true
            divImageError.hidden = false
            divImage.hidden = true
            outputError.value = "Esses valores não são possíveis."

        } else if (catetos == false) {

            outputErrorHidden.hidden = false
            outHidden.hidden = true
            divImageError.hidden = false
            divImage.hidden = true
            outputError.value = "O comprimento da Hipotenusa não pode ser menor ou maior que a soma dos Catetos A e B."

        } else {
            //Chama função para calcular segmentos da hipotenusa
            let segmento1 = calcularSegmento1(catetoA, Number(hipotenusa.value))
            let segmento2 = calcularSegmento2(segmento1, Number(hipotenusa.value))

            //Altura hipotenusa
            let altura = calcularAltura(segmento1, segmento2)

            //Chama funções para calculo dos ângulos
            let anguloAlpha = calcularAnguloAlpha(catetoA, Number(hipotenusa.value))
            let anguloBeta = calcularAnguloBeta(anguloAlpha)

            //Área do triângulo
            let area = calcularArea(altura, Number(hipotenusa.value))

            //Chama função para escolha de figura
            let image = imageChoice(Number(catetoA.value), Number(catetoB.value))

            outHidden.hidden = false
            outputErrorHidden.hidden = true
            divImage.hidden = false
            out1.value = "Comprimento do Cateto A: " + catetoA.toFixed(1)
            out2.value = "Comprimento do Cateto B: " + catetoB.value
            out3.value = "Hipotenusa: " + hipotenusa.value
            out4.value = "Segmentos da Hipotenusa: 1º - " + segmento1.toFixed(1) + " e 2º - " + segmento2.toFixed(1)
            out5.value = "Altura: " + altura.toFixed(1)
            out6.value = "Área: " + area.toFixed(1) + " m²"
            out7.value = "Ângulos: Alpha (α) - " + anguloAlpha.toFixed(1) + "º e " + " Beta (β) - " + anguloBeta.toFixed(1) + "º"
        }
    } else if (catetoA.value && hipotenusa.value) {

        //Chama função para calcular Cateto A
        let catetoB: any = calcularCatetoB(Number(catetoA.value), Number(hipotenusa.value))
        let catetos: boolean = verificarCatetos(Number(catetoA), Number(catetoB.value), Number(hipotenusa.value))

        if (Number.isNaN(catetoB)) {

            outputErrorHidden.hidden = false
            outHidden.hidden = true
            divImageError.hidden = false
            divImage.hidden = true
            outputError.value = "Esses valores não são possíveis."

        } else if (catetos == false) {

            outputErrorHidden.hidden = false
            outHidden.hidden = true
            divImageError.hidden = false
            divImage.hidden = true
            outputError.value = "O comprimento da Hipotenusa não pode ser menor ou maior que a soma dos Catetos A e B."

        } else {
            //Chama função para calcular segmentos da hipotenusa
            let segmento1 = calcularSegmento1(Number(catetoA.value), Number(hipotenusa.value))
            let segmento2 = calcularSegmento2(segmento1, Number(hipotenusa.value))

            //Altura hipotenusa
            let altura = calcularAltura(segmento1, segmento2)

            //Chama funções para calculo dos ângulos
            let anguloAlpha = calcularAnguloAlpha(Number(catetoA.value), Number(hipotenusa.value))
            let anguloBeta = calcularAnguloBeta(anguloAlpha)

            //Área do triângulo
            let area = calcularArea(altura, Number(hipotenusa.value))

            //Chama função para escolha de figura
            let image = imageChoice(Number(catetoA.value), Number(catetoB.value))

            outHidden.hidden = false
            outputErrorHidden.hidden = true
            divImage.hidden = false
            out1.value = "Comprimento do Cateto A: " + catetoA.value
            out2.value = "Comprimento do Cateto B: " + catetoB.toFixed(1)
            out3.value = "Hipotenusa: " + hipotenusa.value
            out4.value = "Segmentos da Hipotenusa: 1º - " + segmento1.toFixed(1) + " e 2º - " + segmento2.toFixed(1)
            out5.value = "Altura: " + altura.toFixed(1)
            out6.value = "Área: " + area.toFixed(1) + " m²"
            out7.value = "Ângulos: Alpha (α) - " + anguloAlpha.toFixed(1) + "º e " + " Beta (β) - " + anguloBeta.toFixed(1) + "º"

        }
    }
}

/*
 * Função Limpar
 * Descrição: realiza a limpeza dos campos na tela
 * Parâmetros: none
 * Retorno: none
 */
btnLimpar.onclick = function () {

    //Limpa os campos
    catetoA.value = ""
    catetoB.value = ""
    hipotenusa.value = ""

    //Esconde o Output
    outHidden.hidden = true
    outputErrorHidden.hidden = true

    //Retorna a imagem primária
    divImage.hidden = false
    divImageError.hidden = true
    image1.hidden = false
    image2.hidden = true
    image3.hidden = true

}

/*
 * Funções para escolha da figura
 * Descrição: 
 *  - testa os valores dos catetos ofertados e verificar a figura mais adequada.
 * Parâmetros: valor dos catetos
 * Retorno: variado
 */

function imageChoice(catetoA: number, catetoB: number) {

    if (catetoA == catetoB) {
        image1.hidden = false
        image2.hidden = true
        image3.hidden = true
        divImageError.hidden = true

    } else if (catetoA > catetoB) {
        image2.hidden = false
        image1.hidden = true
        image3.hidden = true
        divImageError.hidden = true

    } else if (catetoA < catetoB) {
        image3.hidden = false
        image1.hidden = true
        image2.hidden = true
        divImageError.hidden = true
    }
}

/*
 * Funções de cálculo
 * Descrição: 
 *  - Hipotenusa, segmentos 1 e 2, altura, área, ângulos Alpha e Beta.
 * Parâmetros: variados
 * Retorno: variado
 */


//Hipotenusa
function calcularHipotenusa(catetoA: number, catetoB: number) {

    //a^2 + b^2 = c^2
    let hipotenusa: number = ((catetoA ** 2) + (catetoB ** 2)) ** (1 / 2)
    return hipotenusa
}

//Cateto A
function calcularCatetoA(catetoB: number, hipotenusa: number) {
    //a^2 + b^2 = c^2
    //aˆ2 = cˆ2 - bˆ2
    let catetoA: number = ((hipotenusa ** 2) - (catetoB ** 2)) ** (1 / 2)
    return catetoA
}

//Cateto B
function calcularCatetoB(catetoA: number, hipotenusa: number) {

    //a^2 + b^2 = c^2
    //bˆ2 = cˆ2 - aˆ2
    let catetoB: number = ((hipotenusa ** 2) - (catetoA ** 2)) ** (1 / 2)
    return catetoB
}

//Verficar se é um triângulo retângulo
function verificarValores(catetoA: number, catetoB: number, hipotenusa: number) {

    if (((catetoA * catetoA) + (catetoB * catetoB)) === (hipotenusa * hipotenusa)) {
        return true
    } else {
        return false
    }
}

//Converter valores negativos
function converterValor(valor: number) {

    if (valor < 0) {
        valor = (valor  * (-1))
    } 
    if (valor = 0) {
        valor = 1
    }

    return valor
}

//Verficar a possibilidade dos valores dos catetos
function verificarCatetos(catetoA: number, catetoB: number, hipotenusa: number) {

    if (((catetoA * catetoA) + (catetoB * catetoB)) < (hipotenusa * hipotenusa) || ((catetoA * catetoA) + (catetoB * catetoB)) > (hipotenusa * hipotenusa)) {
        return false
    } else {
        return true
    }
}

//Primeiro segmento da hipotenusa
function calcularSegmento1(lado: number, hipotenusa: number) {

    let segmento1: number = (lado ** 2) / hipotenusa
    return segmento1
}

//Segundo segmento da hipotenusa
function calcularSegmento2(lado: number, hipotenusa: number) {

    let segmento2: number = (hipotenusa - lado)
    return segmento2
}

//Altura
function calcularAltura(segmento1: number, segmento2: number) {

    let altura: number = (segmento1 * segmento2) ** (1 / 2)
    return altura
}

//Área
function calcularArea(altura: number, hipotenusa: number) {

    let area: number = (hipotenusa * altura) / 2
    return area
}

//Ângulo Alpha
function calcularAnguloAlpha(lado: number, hipotenusa: number) {

    let arcoSenoAlpha: number = Math.asin(Number(lado) / hipotenusa)

    //Conversão do valor para graus (90º = PI/2)
    let anguloAlpha: number = (arcoSenoAlpha * 90) / (Math.PI / 2)
    return anguloAlpha
}

//Ângulo Beta
function calcularAnguloBeta(lado: number) {

    let anguloBeta = (90 - lado)
    return anguloBeta
}