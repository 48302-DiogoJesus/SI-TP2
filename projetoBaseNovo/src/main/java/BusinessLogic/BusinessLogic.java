package BusinessLogic;

import BusinessLogic.Handlers.d.RemoveClienteParticular;
import BusinessLogic.Handlers.d.InsertClienteParticular;
import BusinessLogic.Handlers.d.UpdateClienteParticular;
import BusinessLogic.Handlers.e.TotalDeAlarmes;
import BusinessLogic.Handlers.f.HandleRegistos;

public class BusinessLogic
{
    // d)
    static public Handler removeClienteParticular = RemoveClienteParticular::run;
    static public Handler insertClienteParticular = InsertClienteParticular::run;
    static public Handler updateClienteParticular = UpdateClienteParticular::run;

    // e
    static public Handler totalDeAlarmes = TotalDeAlarmes::run;

    // f
    static public Handler handleRegistos = HandleRegistos::run;
}
