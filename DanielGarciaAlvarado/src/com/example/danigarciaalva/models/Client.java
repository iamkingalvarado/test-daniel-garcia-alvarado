package com.example.danigarciaalva.models;

/*
 * 
 * POJO Cliente
 * 
 * Se añaden todos los atributes que contiene el servicio web para poder crearlos automáticamente
 * con GSON
 * 
 */
public class Client {

	private int idCliente;
	private String nombre;
	private String rfc;
	private String email;
    private String nameVendedor;
    private int descuento;
    private int credito;
    private int diasCredito;
    private int saldoCredito;
    private int status;
    private int precio;
    private int haveCredito;
    private int exederCredito;
    private int facturasVencidas;
    private int isnew;
    
    public Client(int idCliente, String nombre, String rfc, String email,
			String nameVendedor, int descuento, int credito, int diasCredito,
			int saldoCredito, int status, int precio, int haveCredito,
			int exederCredito, int facturasVencidas, int isnew) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.rfc = rfc;
		this.email = email;
		this.nameVendedor = nameVendedor;
		this.descuento = descuento;
		this.credito = credito;
		this.diasCredito = diasCredito;
		this.saldoCredito = saldoCredito;
		this.status = status;
		this.precio = precio;
		this.haveCredito = haveCredito;
		this.exederCredito = exederCredito;
		this.facturasVencidas = facturasVencidas;
		this.isnew = isnew;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNameVendedor() {
		return nameVendedor;
	}

	public void setNameVendedor(String nameVendedor) {
		this.nameVendedor = nameVendedor;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	public int getCredito() {
		return credito;
	}

	public void setCredito(int credito) {
		this.credito = credito;
	}

	public int getDiasCredito() {
		return diasCredito;
	}

	public void setDiasCredito(int diasCredito) {
		this.diasCredito = diasCredito;
	}

	public int getSaldoCredito() {
		return saldoCredito;
	}

	public void setSaldoCredito(int saldoCredito) {
		this.saldoCredito = saldoCredito;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getHaveCredito() {
		return haveCredito;
	}

	public void setHaveCredito(int haveCredito) {
		this.haveCredito = haveCredito;
	}

	public int getExederCredito() {
		return exederCredito;
	}

	public void setExederCredito(int exederCredito) {
		this.exederCredito = exederCredito;
	}

	public int getFacturasVencidas() {
		return facturasVencidas;
	}

	public void setFacturasVencidas(int facturasVencidas) {
		this.facturasVencidas = facturasVencidas;
	}

	public int getIsnew() {
		return isnew;
	}

	public void setIsnew(int isnew) {
		this.isnew = isnew;
	}
}
