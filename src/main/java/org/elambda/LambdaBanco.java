package org.elambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class LambdaBanco implements RequestHandler<BancoRequest, BancoResponse> {

    @Override
    public BancoResponse handleRequest(BancoRequest bancoRequest, Context context) {

        MathContext context1 = MathContext.DECIMAL128;

        BigDecimal amount = bancoRequest.getAmount().setScale(2, RoundingMode.HALF_UP);
        BigDecimal tasaMensual = bancoRequest.getRate().setScale(2, RoundingMode.HALF_UP).divide(BigDecimal.valueOf(100), context1);
        BigDecimal tasaConDescuento = bancoRequest.getRate().setScale(2, RoundingMode.HALF_UP).divide(BigDecimal.valueOf(100)).subtract(BigDecimal.valueOf(0.2), context1);
        Integer plazo = bancoRequest.getTerm();

        BigDecimal cuotaMensual = this.calcularCuota(amount, tasaMensual, plazo, context1);
        BigDecimal tarjetaConDescuento = this.calcularCuota(amount, tasaConDescuento, plazo, context1);

        BancoResponse bancoResponse = new BancoResponse();
        bancoResponse.setCuota(cuotaMensual);
        bancoResponse.setRate(tasaConDescuento);
        bancoResponse.setTerm(plazo);
        bancoResponse.setRateWithAccount(tarjetaConDescuento);
        bancoResponse.setCuotaWithAccount(tarjetaConDescuento);
        bancoResponse.setTermWithAccount(plazo);
        return bancoResponse;
    }

    public BigDecimal calcularCuota(BigDecimal amount, BigDecimal rate, Integer term, MathContext mathContext){

        BigDecimal tasaPlus = rate.add(BigDecimal.ONE, mathContext);
        BigDecimal tasaPlusN = tasaPlus.pow(term, mathContext);
        BigDecimal tasaPlusNegativa = BigDecimal.ONE.divide(tasaPlusN, mathContext);
        BigDecimal numerador = amount.multiply(rate, mathContext);
        BigDecimal denominador = BigDecimal.ONE.subtract(tasaPlusNegativa, mathContext);
        BigDecimal montoFinal = numerador.divide(denominador, mathContext);

        montoFinal = montoFinal.setScale(2, RoundingMode.HALF_UP);
        return montoFinal;
    }
}
