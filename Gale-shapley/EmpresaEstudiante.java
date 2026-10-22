public class EmpresaEstudiante {
    private int[] empresas;
    private int[] estudiantes;
    private int[][] pref_emp;
    private int[][] pref_est;
    private int[] parEst;
    private int[] parEmp;

    public EmpresaEstudiante(int[] empresas, int[] estudiantes, int[][] pref_emp,
            int[][] pref_est) {
        this.empresas = empresas;
        this.estudiantes = estudiantes;
        this.pref_emp = pref_emp;
        this.pref_est = pref_est;
        this.parEst = new int[estudiantes.length];
        this.parEmp = new int[empresas.length];
        for (int i = 0; i < parEst.length; i++) {
            parEst[i] = -1;
        }
        for (int i = 0; i < parEmp.length; i++) {
            parEmp[i] = -1;
        }
    }

    public void empresas_proponen() {
        int empActual = 0, estEle, empAsign;
        int[] sigProp = new int[empresas.length];
        for (int i = 0; i < empresas.length; i++) {
            sigProp[i] = 0;
        }
        while (objetoLibre(parEmp) != -1) {
            empActual = objetoLibre(parEmp);
            if (sigProp[empActual] >= estudiantes.length) {
                parEmp[empActual] = -1;
            } else {
                estEle = pref_emp[empActual][sigProp[empActual]];
                sigProp[empActual]++;
                if (parEst[estEle] == -1) {
                    parEst[estEle] = empActual;
                    parEmp[empActual] = estEle;
                } else {
                    empAsign = parEst[estEle];
                    if (pref_est[estEle][empActual] < pref_est[estEle][empAsign]) {
                        parEst[estEle] = empActual;
                        parEmp[empActual] = estEle;
                        parEmp[empAsign] = -1;
                    }
                }
            }
        }
    }

    public void estudiantes_proponen() {
        int estActual = 0, empEle, empAsign;
        int[] sigProp = new int[estudiantes.length];
        for (int i = 0; i < estudiantes.length; i++) {
            sigProp[i] = 0;
        }
        while (objetoLibre(parEmp) != -1) {
            estActual = objetoLibre(parEmp);
            if (sigProp[estActual] >= empresas.length) {
                parEmp[estActual] = -1;
            } else {
                empEle = pref_est[estActual][sigProp[estActual]];
                sigProp[estActual]++;
                if (parEst[empEle] == -1) {
                    parEst[empEle] = estActual;
                    parEmp[estActual] = empEle;
                } else {
                    empAsign = parEst[empEle];
                    if (pref_est[empEle][estActual] < pref_est[empEle][empAsign]) {
                        parEst[empEle] = estActual;
                        parEmp[estActual] = empEle;
                        parEmp[empAsign] = -1;
                    }
                }
            }
        }
    }

    private int objetoLibre(int[] parejas) {
        for (int i = 0; i < parejas.length; i++) {
            if (parejas[i] == -1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] empresas = { 0, 1, 2, 3 };
        int[] estudiantes = { 0, 1, 2, 3 };
        int[][] pref_emp = { { 0, 1, 2, 3 }, { 2, 1, 3, 0 }, { 3, 1, 2, 0 }, { 2, 3, 0, 1 } }; // Preferencias
                                                                                               // de las
        // empresas
        int[][] pref_est = { { 2, 0, 3, 1 }, { 1, 3, 2, 0 }, { 3, 2, 1, 0 }, { 1, 3, 0, 2 } }; // Preferencias
                                                                                               // de los
        // estudiantes

        EmpresaEstudiante empEst = new EmpresaEstudiante(empresas, estudiantes, pref_emp,
                pref_est);
        empEst.empresas_proponen();
        empEst.estudiantes_proponen();
    }
}
