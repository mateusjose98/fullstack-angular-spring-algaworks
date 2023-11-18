export class Categoria {
  id?: number;
  nome?: string;
}

export class Endereco {
  logradouro?: string;
  numero?: string;
  complemento?: string;
  bairro?: string;
  cep?: string;
  cidade?: string;
  estado?: string;
}

export class Pessoa {
  id?: number;
  nome?: string;
  endereco: Endereco = new Endereco();
  ativo: boolean = true;
}

export class Lancamento {
  id?: number;
  descricao?: string;
  dataVencimento?: Date;
  dataPagamento?: Date;
  valor?: number;
  obs?: string;
  tipo: string = 'RECEITA';
  categoria: Categoria = new Categoria();
  pessoa: Pessoa = new Pessoa();
}
