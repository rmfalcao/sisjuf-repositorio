COMMENT ON TABLE banco IS 'Bancos da Asserjuf.';
COMMENT ON COLUMN banco.seq_banco IS 'Chave de identifica��o do banco.';
COMMENT ON COLUMN banco.num_banco IS 'N�mero do banco.';
COMMENT ON COLUMN banco.nom_banco IS 'Nome do banco.';
COMMENT ON COLUMN banco.sig_banco IS 'Sigla do banco.';
--------------------------------
COMMENT ON TABLE baixa_lancamento IS 'Baixas/Efetiva��o de lan�amentos.';
COMMENT ON COLUMN baixa_lancamento.seq_baixa_lancamento IS 'Chave de identifica��o da baixa.';
COMMENT ON COLUMN baixa_lancamento.seq_lancamento IS 'Chave de identifica��o do lan�amento ao qual a baixa pertence.';
COMMENT ON COLUMN baixa_lancamento.dat_baixa_lancamento IS 'Data da baixa.';
COMMENT ON COLUMN baixa_lancamento.val_baixa_lancamento IS 'Valor da baixa.';
--------------------------------
COMMENT ON TABLE conta IS 'Contas que a Asserjuf movimenta.';
COMMENT ON COLUMN conta.seq_conta IS 'Chave de identifica��o da conta.';
COMMENT ON COLUMN conta.nom_conta IS 'Nome atribu�do � conta.';
--------------------------------
COMMENT ON TABLE conta_banco IS 'Dados banc�rios das contas que a Asserjuf movimenta.';
COMMENT ON COLUMN conta_banco.seq_conta IS 'Chave de identifica��o da conta.';
COMMENT ON COLUMN conta_banco.seq_banco IS 'Chave de identifica��o do banco ao qual a conta pertence.';
COMMENT ON COLUMN conta_banco.num_agencia_conta IS 'N�mero da ag�ncia da conta.';
COMMENT ON COLUMN conta_banco.dig_agencia_conta IS 'D�gito verificador do n�mero da ag�ncia da conta.';
COMMENT ON COLUMN conta_banco.num_conta IS 'N�mero da conta.';
COMMENT ON COLUMN conta_banco.dig_conta IS 'D�gito verificador do n�mero da conta.';
COMMENT ON COLUMN conta_banco.cod_operacao_conta IS 'C�digo de opera��o da conta (para Caixa Econ�mica Federal).';
COMMENT ON COLUMN conta_banco.seq_tipo_conta IS 'Chave de identifica��o do tipo da conta (Conta corrente ou poupan�a).';
COMMENT ON COLUMN conta_banco.nom_titular_conta IS 'Nome ou Raz�o Social do titular da conta.';
COMMENT ON COLUMN conta_banco.cpf_cnpj_titular_conta IS 'CPF ou CNPJ do titular da conta.';
--------------------------------
COMMENT ON TABLE historico_saldo IS 'Hist�rico/Consolida��o de saldo das contas dia-a-dia, obtidos no in�cio do dia.';
COMMENT ON COLUMN historico_saldo.seq_conta IS 'Chave de identifica��o da conta.';
COMMENT ON COLUMN historico_saldo.dat_historico_saldo IS 'Data da consolida��o do saldo.';
COMMENT ON COLUMN historico_saldo.val_historico_saldo IS 'Valor consolidado do saldo no in�cio do dia.';
--------------------------------
COMMENT ON TABLE lancamento IS 'Lan�amentos financeiros.';
COMMENT ON COLUMN lancamento.seq_lancamento IS 'Chave de identifica��o do lan�amento.';
COMMENT ON COLUMN lancamento.seq_conta IS 'Chave de identifica��o da conta.';
COMMENT ON COLUMN lancamento.seq_origem_lancamento IS 'Chave de identifica��o da origem do lan�amento (unidade de servi�o da Asserjuf: Xerox, Conv�nios, etc.).';
COMMENT ON COLUMN lancamento.seq_tipo_lancamento IS 'Chave de identifica��o do tipo de lan�amento (permite uma classifica��o para os lan�amentos de origem USU�RIO).';
COMMENT ON COLUMN lancamento.seq_tipo_operacao IS 'Chave de identifica��o do tipo de opera��o do lan�amento (CR�DITO, D�BITO, A CREDITAR OU A DEBITAR).';
COMMENT ON COLUMN lancamento.dat_previsao_lancamento IS 'Data prevista para os lan�amentos de tipos de opera��o de agendamento.';
COMMENT ON COLUMN lancamento.val_lancamento IS 'Valor do lan�amento.';
COMMENT ON COLUMN lancamento.des_lancamento IS 'Descri��o livre para o lan�amento.';
COMMENT ON COLUMN lancamento.dat_efetivacao_lancamento IS 'Data em que o lan�amento foi efetivado/quitado.';
COMMENT ON COLUMN lancamento.tabela_x IS 'Nome da tabela que faz interface com outros m�dulos.';
COMMENT ON COLUMN lancamento.nom_campo_x IS 'Nome do campo na tabela de interface com outros m�dulos.';
COMMENT ON COLUMN lancamento.val_campo_x IS 'Valor do campo na tabela de interface com outros m�dulos.';
--------------------------------
COMMENT ON TABLE origem_lancamento IS 'Origem do lan�amento (unidade de servi�o da Asserjuf: Xerox, Conv�nios, etc.).';
COMMENT ON COLUMN origem_lancamento.seq_origem_lancamento IS 'Chave de identifica��o da Origem de lan�amento.';
COMMENT ON COLUMN origem_lancamento.nom_origem_lancamento IS 'Nome descritivo da origem de lan�amento.';
COMMENT ON COLUMN origem_lancamento.flg_ativo_origem_lancamento IS 'Flag que identifica se a origem de lan�amento est� ativa para uso nos lan�amentos financeiros.';
COMMENT ON COLUMN origem_lancamento.flg_nao_apagavel_origem_lancamento IS 'Flag que identifica se o registro da origem de lan�amentos pode ser apagado ou n�o.';
--------------------------------
COMMENT ON TABLE parametros IS 'Par�metros utilizados no sistema para facilitar o desenvolvimento e manuten��o.';
COMMENT ON COLUMN parametros.nom_parametro IS 'Nome do par�metro.';
COMMENT ON COLUMN parametros.str_val_parametro IS 'Valor do par�metro.';
--------------------------------
COMMENT ON TABLE tipo_conta IS 'Tipo da conta (Conta corrente ou poupan�a).';
COMMENT ON COLUMN tipo_conta.seq_tipo_conta IS 'Chave de identifica��o do tipo de conta.';
COMMENT ON COLUMN tipo_conta.des_tipo_conta IS 'Nome descritivo do tipo de conta.';
--------------------------------
COMMENT ON TABLE tipo_lancamento IS 'Tipo de lan�amento (permite uma classifica��o para os lan�amentos de origem USU�RIO).';
COMMENT ON COLUMN tipo_lancamento.seq_tipo_lancamento IS 'Chave de identifica��o do tipo de lan�amento.';
COMMENT ON COLUMN tipo_lancamento.nom_tipo_lancamento IS 'Nome descritivo do tipo de lan�amento.';
COMMENT ON COLUMN tipo_lancamento.flg_nao_apagavel_tipo_lancamento IS 'Flag que identifica se o registro do tipo de lan�amentos pode ser apagado ou n�o.';
--------------------------------
COMMENT ON TABLE tipo_operacao IS 'Tipo de opera��o do lan�amento (CR�DITO, D�BITO, A CREDITAR OU A DEBITAR).';
COMMENT ON COLUMN tipo_operacao.seq_tipo_operacao IS 'Chave de identifica��o do tipo de opera��o.';
COMMENT ON COLUMN tipo_operacao.nom_tipo_operacao IS 'Nome descritivo do tipo de opera��o.';
COMMENT ON COLUMN tipo_operacao.sig_tipo_operacao IS 'Sigla do tipo de opera��o.';
COMMENT ON COLUMN tipo_operacao.flg_previsao_tipo_operacao IS 'Flag que identifica se o tipo de opera��o � de previs�o.';
COMMENT ON COLUMN tipo_operacao.flg_debito_tipo_operacao IS 'Flag que identifica se o tipo de opera��o � de d�bito.';
