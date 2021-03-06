package BusinessLogic.Handlers.d;

import Utils.UI_Utils;
import Utils.Utils.ProcedureType;
import Utils.Utils.ReturnType;
import java.util.ArrayList;

import Utils.Utils.Parameter;

import model.EntityParameters;

import static Utils.Utils.CallProcedure;

public class RemoveClienteParticular {

    public static void run() throws Exception {
        Parameter nif = EntityParameters.NIF(false, true);

        Boolean result = UI_Utils.getMultipleInputs(new ArrayList<>() {
            {add(nif);}
        });

        if (!result)
            return;

        Parameter[] args = { nif };
        CallProcedure(
                "remove_cliente_particular",
                args,
                ProcedureType.STORED_PROCEDURE,
                ReturnType.VOID
        );
        System.out.println("[DONE] Cliente Removido");
    }
}
