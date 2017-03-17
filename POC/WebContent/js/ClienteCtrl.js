let linkservice = "http://localhost:8080/POC/rest/cliente/";

const app = angular.module("Cliente", []);

app.controller("ClienteCrud", function ($scope, $http){
	
	/*SELECT DE TODOS OS CLIENTES DA APP*/
	$http.get(linkservice + "selectClientes").then(function (response){
		$scope.response = response.data;
	});
	
	$scope.ordenar = function(campo){
		$scope.criterio = campo;
		return $scope.criterio;
	}
	
	$scope.formCadastro = function(){
		window.location.assign("http://localhost:8080/POC/formCadastrarCliente.html");
	}
	
	$scope.addSessionStorage = function(cliente){
		window.sessionStorage.cliente = JSON.stringify(cliente);
		window.location.href = "formAlterarCliente.html";
	}
	
	$scope.deletar = function deletar(cliente){
		
		const pergunta = confirm("Deseja realmente escluir esse cliente?");
		if (pergunta == true) {
			
			$http.post(linkservice + "deletar", cliente).then(function(){
				alert("Cliente deletado com sucesso!");
				window.location.href = "consultarCliente.html";
			});
			
		} else {
		    alert("Operação cancelada!");
		}
		
	}
})

app.controller("ClienteInserir", function($scope, $http){

	/*SELECT DE TODAS AS CATEGORIAS DA APP*/
	$http.get(linkservice + "selectCategoria").then(function (response){
		$scope.categorias = response.data;
	});
	
	$scope.validaCPF = function(){
		
		if(isNaN($scope.cliente.cpf)){
			alert("Digite apenas números!");
			$scope.cliente.cpf = "";
		}
		
		var cpf = $scope.cliente.cpf;
		var valido = TestaCPF(cpf);
		
		if(!valido){
			alert("C.P.F inválido!");
			$scope.cliente.cpf = "";
		}
	}
	
	$scope.validaRG = function(){
		if(isNaN($scope.cliente.rg)){
			alert("Digite apenas números!");
			$scope.cliente.rg = "";
		}
	}
	
	$scope.inserir = function inserir(){
		$http.post(linkservice + "inserir", $scope.cliente).then(function(){
			alert("Cliente inserido com sucesso!");
			window.location.href = "consultarCliente.html";
		});
	}
	
	
	$scope.buscaCep = function(){
	
		$http.get("//viacep.com.br/ws/" + $scope.cliente.cep + "/json/").then(function(data){
			console.log(data);
			$scope.cliente.logradouro = data.data.logradouro;
			$scope.cliente.bairro = data.data.bairro;
			$scope.cliente.complemento = data.data.complemento;
			$scope.cliente.cidade = data.data.localidade;
			$scope.cliente.estado = data.data.uf;
			
		}).catch(function(response){
			alert("Não foi possível buscar este CEP");
			$scope.cliente.cep = null;
			$scope.cliente.logradouro = null;
			$scope.cliente.bairro = null;
			$scope.cliente.complemento = null;
			$scope.cliente.cidade = null;
			$scope.cliente.estado = null;
		});
	}
	
	$scope.voltar = function voltar(){
		window.location.href = "consultarCliente.html";
	}
	
})

app.controller("ClienteAtualizar", function($scope, $http){
	$scope.cliente = JSON.parse(window.sessionStorage.cliente);
	
	/*SELECT DE TODAS AS CATEGORIAS DA APP*/
	$http.get(linkservice + "selectCategoria").then(function (response){
		$scope.categorias = response.data;
	});
	
	$scope.validaCPF = function(){
		
		alert("entrei");
		
		if(isNaN($scope.cliente.cpf)){
			alert("Digite apenas números!");
			$scope.cliente.cpf = "";
		}
		
		var cpf = $scope.cliente.cpf;
		var valido = TestaCPF(cpf);
		
		if(!valido){
			alert("C.P.F inválido!");
			$scope.cliente.cpf = "";
		}
	}
	
	$scope.validaRG = function(){
		if(isNaN($scope.cliente.rg)){
			alert("Digite apenas números!");
			$scope.cliente.rg = "";
		}
	}
	
	$scope.buscaCep = function(){
		$http.get("//viacep.com.br/ws/" + $scope.cliente.cep + "/json/").then(function(data){
			console.log(data);
			$scope.cliente.logradouro = data.data.logradouro;
			$scope.cliente.bairro = data.data.bairro;
			$scope.cliente.complemento = data.data.complemento;
			$scope.cliente.cidade = data.data.localidade;
			$scope.cliente.estado = data.data.uf;
		}).catch( function(response){
			alert("Não foi possível buscar este CEP");
			$scope.cliente.cep = null;
			$scope.cliente.logradouro = null;
			$scope.cliente.numero = null;
			$scope.cliente.bairro = null;
			$scope.cliente.complemento = null;
			$scope.cliente.cidade = null;
			$scope.cliente.estado = null;
		});	
	}
	
	
	$scope.atualizar = function atualizar(){
		$http.post(linkservice + "atualizar", $scope.cliente).then(function(){
			alert("Informações do cliente " + $scope.cliente.nome + " atualizadas com sucesso!");
			window.location.href = "consultarCliente.html";
		});
	}
	
	$scope.voltar = function voltar(){
		window.location.href = "consultarCliente.html";
	}
	
	$scope.validaRG = function(){
		if(isNaN($scope.cliente.rg)){
			alert("Digite apenas números!");
			$scope.cliente.rg = "";
		}
	}
	
	$scope.validaCPF = function(){
		if(isNaN($scope.cliente.cpf)){
			alert("Digite apenas números!");
			$scope.cliente.cpf = "";
		}
	}
	
})


function TestaCPF(strCPF) {
    var Soma;
    var Resto;
    Soma = 0;
	if (strCPF == "00000000000") return false;
    
	for (i=1; i<=9; i++) Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (11 - i);
	Resto = (Soma * 10) % 11;
	
    if ((Resto == 10) || (Resto == 11))  Resto = 0;
    if (Resto != parseInt(strCPF.substring(9, 10)) ) return false;
	
	Soma = 0;
    for (i = 1; i <= 10; i++) Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (12 - i);
    Resto = (Soma * 10) % 11;
	
    if ((Resto == 10) || (Resto == 11))  Resto = 0;
    if (Resto != parseInt(strCPF.substring(10, 11) ) ) return false;
    return true;
}

