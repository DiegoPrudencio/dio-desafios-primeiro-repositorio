// O código abaixo tem alguns erros e não funciona como deveria. Você pode identificar quais são e corrigi-los em um arquivo TS?

let botaoAtualizar = document.getElementById('atualizar-saldo') as HTMLButtonElement;
let botaoLimpar = document.getElementById('limpar-saldo') as HTMLButtonElement;
let soma = document.getElementById('soma') as HTMLInputElement;
let campoSaldo = document.getElementById('campo-saldo') as HTMLSpanElement;

let saldo: number = 0

function somarAoSaldo(soma: number) {
    saldo += soma
    campoSaldo.innerHTML = saldo.toString();
    limparImput()
}

function limparSaldo() {
    campoSaldo.innerHTML = '';
    limparImput()
}

function limparImput() {
    soma.value = '';
}

botaoAtualizar.addEventListener('click', function () {
    somarAoSaldo(Number(soma.value));
});

botaoLimpar.addEventListener('click', function () {
    limparSaldo();
});
