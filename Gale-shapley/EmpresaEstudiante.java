public class EmpresaEstudiante {
    private int[] empresas;
    private int[] estudiantes;
    private int[][] preferencias_Empresas;
    private int[][] preferencias_Estudiantes;
    private int[] parejas;

    public EmpresaEstudiante(int[] empresas, int[] estudiantes, int[][] preferencias_Empresas,
            int[][] preferencias_Estudiantes) {
        this.empresas = empresas;
        this.estudiantes = estudiantes;
        this.preferencias_Empresas = preferencias_Empresas;
        this.preferencias_Estudiantes = preferencias_Estudiantes;
        this.parejas = new int[empresas.length];
        for (int i = 0; i < parejas.length; i++) {
            parejas[i] = -1; // todos inicialmente están libres
        }
    }

    public void empresas_proponen() {
        boolean[] empresasLibres = new boolean[empresas.length];
        int empresaActual = 0, estudianteAsignado, siguienteEmpresa, posEmpresa = 0, posEstudiante = 0,
                estudianteElegido;
        for (int i = 0; i < empresasLibres.length; i++) {
            empresasLibres[i] = true; // Todas las empresas están libres al inicio

        }
        while (empresaLibre(empresasLibres) != -1) {
            empresaActual = empresaLibre(empresasLibres);
            estudianteElegido = preferencias_Empresas[empresaActual][posEstudiante];
            estudianteAsignado = parejas[empresaActual]; // Obtener el estudiante asignado a la empresa actual
            posEmpresa = empresaActual + 1; // Obtener la posición de la empresa actual en la lista de preferencias del
                                            // estudiante
            if (estudianteAsignado == -1) {// la empresa está libre
                parejas[estudianteElegido] = empresaActual;
                empresasLibres[empresaActual] = false;
                posEstudiante++;
            } else {
                siguienteEmpresa = preferencias_Estudiantes[estudianteAsignado][posEmpresa];
                if (empresaActual < siguienteEmpresa) {// la siguiente empresa es mejor que la actual
                    parejas[empresaActual] = preferencias_Empresas[siguienteEmpresa][posEstudiante];
                    empresasLibres[empresaActual] = true;
                    empresasLibres[siguienteEmpresa] = false;
                    posEstudiante++;
                } else {
                    posEmpresa++;
                }
            }
        }
    }

    private int empresaLibre(boolean[] empresasLibres) {
        for (int i = 0; i < empresasLibres.length; i++) {
            if (empresasLibres[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] empresas = { 0, 1, 2, 3 };
        int[] estudiantes = { 0, 1, 2, 3 };
        int[][] preferencias_Empresas = { { 0, 1, 2, 3 }, { 2, 1, 3, 0 }, { 3, 1, 2, 0 }, { 2, 3, 0, 1 } }; // Preferencias
                                                                                                            // de las
        // empresas
        int[][] preferencias_Estudiantes = { { 2, 0, 3, 1 }, { 1, 3, 2, 0 }, { 3, 2, 1, 0 }, { 1, 3, 0, 2 } }; // Preferencias
                                                                                                               // de los
        // estudiantes

        EmpresaEstudiante empEst = new EmpresaEstudiante(empresas, estudiantes, preferencias_Empresas,
                preferencias_Estudiantes);
        empEst.empresas_proponen();
        System.out.println("Parejas resultantes:");
        for (int i = 0; i < empEst.parejas.length; i++) {
            System.out.println("(Empresa: " + empEst.empresas[i] + ", Estudiante: "
                    + empEst.parejas[i] + ")");
        }
    }
}
