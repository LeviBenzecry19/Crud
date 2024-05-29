

public class Jogo {
    private int id;
    private String nomejg;
    private String empresa;
    private int nota;

    public Jogo(int id, String nomejg, String empresa, int nota) {
        this.id = id;
        this.nomejg = nomejg;
        this.empresa = empresa;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public String getnomejg() {
        return nomejg;
    }

    public void setnomejg(String nomejg) {
        this.nomejg = nomejg;
    }

    public String getempresa() {
        return empresa;
    }

    public void setempresa(String empresa) {
        this.empresa = empresa;
    }

    public int getnota() {
        return nota;
    }

    public void setnota(int nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return id + "," + nomejg + "," + empresa + "," + nota;
    }

    public static Jogo fromString(String line) {
        String[] parts = line.split(",");
        int id = Integer.parseInt(parts[0]);
        String nomejg = parts[1];
        String empresa = parts[2];
        int nota = Integer.parseInt(parts[3]);
        return new Jogo(id, nomejg, empresa, nota);
    }
}