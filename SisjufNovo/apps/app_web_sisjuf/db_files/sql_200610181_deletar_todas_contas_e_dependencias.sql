delete from conta_banco;

delete from historico_saldo;

delete from baixa_lancamento;

delete from lancamento;

delete from conta;

delete from tipo_lancamento
where seq_tipo_lancamento <> 1;

delete from banco;

delete from origem_lancamento 
where seq_origem_lancamento <> 1;


