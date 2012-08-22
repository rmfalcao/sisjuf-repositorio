COMMENT ON TABLE banco IS 'Bancos da Asserjuf.';
COMMENT ON COLUMN banco.seq_banco IS 'Chave de identificação do banco.';
COMMENT ON COLUMN banco.num_banco IS 'Número do banco.';
COMMENT ON COLUMN banco.nom_banco IS 'Nome do banco.';
COMMENT ON COLUMN banco.sig_banco IS 'Sigla do banco.';
--------------------------------
COMMENT ON TABLE baixa_lancamento IS 'Baixas/Efetivação de lançamentos.';
COMMENT ON COLUMN baixa_lancamento.seq_baixa_lancamento IS 'Chave de identificação da baixa.';
COMMENT ON COLUMN baixa_lancamento.seq_lancamento IS 'Chave de identificação do lançamento ao qual a baixa pertence.';
COMMENT ON COLUMN baixa_lancamento.dat_baixa_lancamento IS 'Data da baixa.';
COMMENT ON COLUMN baixa_lancamento.val_baixa_lancamento IS 'Valor da baixa.';
--------------------------------
COMMENT ON TABLE conta IS 'Contas que a Asserjuf movimenta.';
COMMENT ON COLUMN conta.seq_conta IS 'Chave de identificação da conta.';
COMMENT ON COLUMN conta.nom_conta IS 'Nome atribuído à conta.';
--------------------------------
COMMENT ON TABLE conta_banco IS 'Dados bancários das contas que a Asserjuf movimenta.';
COMMENT ON COLUMN conta_banco.seq_conta IS 'Chave de identificação da conta.';
COMMENT ON COLUMN conta_banco.seq_banco IS 'Chave de identificação do banco ao qual a conta pertence.';
COMMENT ON COLUMN conta_banco.num_agencia_conta IS 'Número da agência da conta.';
COMMENT ON COLUMN conta_banco.dig_agencia_conta IS 'Dígito verificador do número da agência da conta.';
COMMENT ON COLUMN conta_banco.num_conta IS 'Número da conta.';
COMMENT ON COLUMN conta_banco.dig_conta IS 'Dígito verificador do número da conta.';
COMMENT ON COLUMN conta_banco.cod_operacao_conta IS 'Código de operação da conta (para Caixa Econômica Federal).';
COMMENT ON COLUMN conta_banco.seq_tipo_conta IS 'Chave de identificação do tipo da conta (Conta corrente ou poupança).';
COMMENT ON COLUMN conta_banco.nom_titular_conta IS 'Nome ou Razão Social do titular da conta.';
COMMENT ON COLUMN conta_banco.cpf_cnpj_titular_conta IS 'CPF ou CNPJ do titular da conta.';
--------------------------------
COMMENT ON TABLE historico_saldo IS 'Histórico/Consolidação de saldo das contas dia-a-dia, obtidos no início do dia.';
COMMENT ON COLUMN historico_saldo.seq_conta IS 'Chave de identificação da conta.';
COMMENT ON COLUMN historico_saldo.dat_historico_saldo IS 'Data da consolidação do saldo.';
COMMENT ON COLUMN historico_saldo.val_historico_saldo IS 'Valor consolidado do saldo no início do dia.';
--------------------------------
COMMENT ON TABLE lancamento IS 'Lançamentos financeiros.';
COMMENT ON COLUMN lancamento.seq_lancamento IS 'Chave de identificação do lançamento.';
COMMENT ON COLUMN lancamento.seq_conta IS 'Chave de identificação da conta.';
COMMENT ON COLUMN lancamento.seq_origem_lancamento IS 'Chave de identificação da origem do lançamento (unidade de serviço da Asserjuf: Xerox, Convênios, etc.).';
COMMENT ON COLUMN lancamento.seq_tipo_lancamento IS 'Chave de identificação do tipo de lançamento (permite uma classificação para os lançamentos de origem USUÁRIO).';
COMMENT ON COLUMN lancamento.seq_tipo_operacao IS 'Chave de identificação do tipo de operação do lançamento (CRÉDITO, DÉBITO, A CREDITAR OU A DEBITAR).';
COMMENT ON COLUMN lancamento.dat_previsao_lancamento IS 'Data prevista para os lançamentos de tipos de operação de agendamento.';
COMMENT ON COLUMN lancamento.val_lancamento IS 'Valor do lançamento.';
COMMENT ON COLUMN lancamento.des_lancamento IS 'Descrição livre para o lançamento.';
COMMENT ON COLUMN lancamento.dat_efetivacao_lancamento IS 'Data em que o lançamento foi efetivado/quitado.';
COMMENT ON COLUMN lancamento.tabela_x IS 'Nome da tabela que faz interface com outros módulos.';
COMMENT ON COLUMN lancamento.nom_campo_x IS 'Nome do campo na tabela de interface com outros módulos.';
COMMENT ON COLUMN lancamento.val_campo_x IS 'Valor do campo na tabela de interface com outros módulos.';
--------------------------------
COMMENT ON TABLE origem_lancamento IS 'Origem do lançamento (unidade de serviço da Asserjuf: Xerox, Convênios, etc.).';
COMMENT ON COLUMN origem_lancamento.seq_origem_lancamento IS 'Chave de identificação da Origem de lançamento.';
COMMENT ON COLUMN origem_lancamento.nom_origem_lancamento IS 'Nome descritivo da origem de lançamento.';
COMMENT ON COLUMN origem_lancamento.flg_ativo_origem_lancamento IS 'Flag que identifica se a origem de lançamento está ativa para uso nos lançamentos financeiros.';
COMMENT ON COLUMN origem_lancamento.flg_nao_apagavel_origem_lancamento IS 'Flag que identifica se o registro da origem de lançamentos pode ser apagado ou não.';
--------------------------------
COMMENT ON TABLE parametros IS 'Parâmetros utilizados no sistema para facilitar o desenvolvimento e manutenção.';
COMMENT ON COLUMN parametros.nom_parametro IS 'Nome do parâmetro.';
COMMENT ON COLUMN parametros.str_val_parametro IS 'Valor do parâmetro.';
--------------------------------
COMMENT ON TABLE tipo_conta IS 'Tipo da conta (Conta corrente ou poupança).';
COMMENT ON COLUMN tipo_conta.seq_tipo_conta IS 'Chave de identificação do tipo de conta.';
COMMENT ON COLUMN tipo_conta.des_tipo_conta IS 'Nome descritivo do tipo de conta.';
--------------------------------
COMMENT ON TABLE tipo_lancamento IS 'Tipo de lançamento (permite uma classificação para os lançamentos de origem USUÁRIO).';
COMMENT ON COLUMN tipo_lancamento.seq_tipo_lancamento IS 'Chave de identificação do tipo de lançamento.';
COMMENT ON COLUMN tipo_lancamento.nom_tipo_lancamento IS 'Nome descritivo do tipo de lançamento.';
COMMENT ON COLUMN tipo_lancamento.flg_nao_apagavel_tipo_lancamento IS 'Flag que identifica se o registro do tipo de lançamentos pode ser apagado ou não.';
--------------------------------
COMMENT ON TABLE tipo_operacao IS 'Tipo de operação do lançamento (CRÉDITO, DÉBITO, A CREDITAR OU A DEBITAR).';
COMMENT ON COLUMN tipo_operacao.seq_tipo_operacao IS 'Chave de identificação do tipo de operação.';
COMMENT ON COLUMN tipo_operacao.nom_tipo_operacao IS 'Nome descritivo do tipo de operação.';
COMMENT ON COLUMN tipo_operacao.sig_tipo_operacao IS 'Sigla do tipo de operação.';
COMMENT ON COLUMN tipo_operacao.flg_previsao_tipo_operacao IS 'Flag que identifica se o tipo de operação é de previsão.';
COMMENT ON COLUMN tipo_operacao.flg_debito_tipo_operacao IS 'Flag que identifica se o tipo de operação é de débito.';
