// Como podemos rodar isso em um arquivo .ts sem causar erros? 

// let employee = {};
// employee.code = 10;
// employee.name = "John";


interface Employee {
    code: number;
    name: string;
}

const newEmployee: Employee = {
    code: 10,
    name: "John",
}
