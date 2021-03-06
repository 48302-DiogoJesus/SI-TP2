package BusinessLogic.Handlers.d;

import Utils.UI_Utils;
import Utils.Utils.ProcedureType;
import Utils.Utils.ReturnType;
import model.EntityParameters;
import Utils.Utils.Parameter;

import java.util.ArrayList;

import static Utils.Utils.CallProcedure;


public class UpdateClienteParticular {

    public static void run() throws Exception {
        Parameter nif = EntityParameters.NIF(false, true);
        Parameter cc = EntityParameters.CC(false);
        Parameter nome = EntityParameters.NOME(false);
        Parameter morada = EntityParameters.MORADA(false);
        Parameter id_referenciador = EntityParameters.REFERENCIADOR(false, true);

        Boolean result = UI_Utils.getMultipleInputs(new ArrayList<>() {
            {add(nif); add(cc); add(nome); add(morada); add(id_referenciador); }
        });

        if (!result)
            return;

        Parameter[] args = { nif, cc, nome, morada, id_referenciador };
        CallProcedure(
                "update_cliente_particular",
                args,
                ProcedureType.STORED_PROCEDURE,
                ReturnType.VOID
        );

        System.out.println("[DONE] Cliente Atualizado");
    }
}

