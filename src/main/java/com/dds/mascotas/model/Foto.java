package com.dds.mascotas.model;

import com.dds.helpers.ImageHelper;
import org.springframework.util.Base64Utils;

import javax.persistence.*;

@Entity
@Table(name = "fotos_mascota")
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fotos_mascota_id_seq")
    @SequenceGenerator(name = "fotos_mascota_id_seq", sequenceName = "fotos_mascota_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "file_name")
    private String name;

    @Column(name = "base_64")
    private String base64;

    //FIXME
    @ManyToOne
    @JoinColumn(name = "id_mascota")
    private Mascota mascota;

    public Foto()
    {}

    public Foto(String name, byte[] bytes) {
        this.name = name;
        this.base64 = Base64Utils.encodeToString(bytes);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    /*    public String getBase64() {
        if (this.bytes == null)
            return "";
        return Base64Utils.encodeToString(this.bytes);
    }*/
    public byte[] getBytes() {
        return Base64Utils.decodeFromString(this.base64);
    }

    public Foto normalizar(ConfiguracionFoto configuracion) throws Exception {
        byte[] normalized = ImageHelper.getInstance().resizeImage(this.getBytes(), configuracion.getAncho(), configuracion.getAlto());
        return new Foto(this.name, normalized);
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }
}
