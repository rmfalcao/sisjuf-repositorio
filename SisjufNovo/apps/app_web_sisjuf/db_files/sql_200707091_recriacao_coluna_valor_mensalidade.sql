alter table valor_mensalidade
drop column val_valor_mensalidade;

alter table valor_mensalidade
add column val_mensalidade numeric(14,2) not null;

COMMENT ON COLUMN valor_mensalidade.val_mensalidade IS 'Valor da mensalidade';