package BusinessLogic.Handlers.f;

import DataScopes.DataScope;
import model.Entities.*;

import java.util.List;

public class HandleRegistosWithOptimisticLocking {
    // IGNORE ARGS FOR NOW, MAYBE REMOVE LATER
    public static void run() throws Exception {

        try (
                DataScope<Registo, Integer> ds_registo = new DataScope<>(Registo.class);
                DataScope<RegistoNProc, Registo> ds_registo_n_proc = new DataScope<>(RegistoNProc.class);
                DataScope<RegistoInvalido, Integer> ds_registo_invalido = new DataScope<>(RegistoInvalido.class);
                DataScope<RegistoProc, Integer> ds_registo_proc = new DataScope<>(RegistoProc.class);
                DataScope<Gps, Integer> ds_gps = new DataScope<>(Gps.class)
        ) {
            //  REGISTOS NÃO PROCESSADOS -> Obter lista de registos não processados
            List<RegistoNProc> RegistosNProc = ds_registo_n_proc.getAll();

            for (RegistoNProc registoNProc: RegistosNProc) {
                Integer registoNProcID = registoNProc.getId_registo().getPK();
                Registo registo = ds_registo.getSingle(registoNProcID);

                if (
                        registo.getLongitude() == null ||
                        registo.getLatitude() == null ||
                        ds_gps.getSingle(registo.getId_gps().getPK()) == null
                ) {
                    // INVÁLIDO -> INVÁLIDOS

                    RegistoInvalido ri = new RegistoInvalido();
                    ri.setId_registo(registo);

                    ds_registo_invalido.create(ri);
                } else {
                    // VÁLIDO -> PROCESSADOS

                    RegistoProc rp = new RegistoProc();
                    rp.setId_registo(registo);

                    ds_registo_proc.create(rp);
                }

                // JÁ FOI PROCESSADO -> Apagar dos não processados
                ds_registo_n_proc.delete(registoNProc);
            }

            ds_registo.validateWork();
            ds_registo_n_proc.validateWork();
            ds_registo_invalido.validateWork();
            ds_registo_proc.validateWork();
            ds_gps.validateWork();
        }

        System.out.println("[DONE] Registos Processados");
    }
}
