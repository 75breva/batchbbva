package com.murex.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "murtable")
@Getter
@Setter
public class Murex {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "fec_proceso")
  private String fec_proceso;

  @Column(name = "cod_nominalpago")
  private String cod_nominalpago;

  @Column(name = "cod_nominalrecib")
  private String cod_nominalrecib;

  @Column(name = "imp_npvpago")
  private Double imp_npvpago;

  @Column(name = "imp_npvrec")
  private Double imp_npvrec;

  @Column(name = "cod_cur_pl")
  private String cod_cur_pl;

  @Column(name = "cod_numoperfront")
  private String cod_numoperfront;

  @Column(name = "imp_irdfpv1")
  private Double imp_irdfpv1;

  @Column(name = "imp_irdfpv2")
  private Double imp_irdfpv2;

  @Column(name = "imp_csfi")
  private Double imp_csfi;

  @Column(name = "imp_csnf")
  private Double imp_csnf;

  @Column(name = "imp_fe")
  private Double imp_fe;

  @Column(name = "imp_fefi")
  private Double imp_fefi;

  @Column(name = "imp_fpnfcp")
  private Double imp_fpnfcp;

  @Column(name = "imp_fpnfrv")
  private Double imp_fpnfrv;

  @Column(name = "imp_ftfi")
  private Double imp_ftfi;

  @Column(name = "imp_delta")
  private Double imp_delta;

  @Column(name = "imp_gamma")
  private Double imp_gamma;

  @Column(name = "imp_rho")
  private Double imp_rho;

  @Column(name = "imp_theta")
  private Double imp_theta;

  @Column(name = "imp_vega")
  private Double imp_vega;

  @Column(name = "imp_zcsnv")
  private Double imp_zcsnv;

  @Column(name = "cod_counterpart")
  private String cod_counterpart;

  @Column(name = "cod_cntrplb")
  private String cod_cntrplb;

  @Column(name = "xti_isinternal")
  private String xti_isinternal;

  @Column(name = "imp_lqty")
  private Double imp_lqty;

  @Column(name = "cod_portfolio")
  private String cod_portfolio;

  @Column(name = "imp_rtccp0")
  private Double imp_rtccp0;

  @Column(name = "imp_rtccp1")
  private Double imp_rtccp1;

  @Column(name = "xti_rtpr0")
  private String xti_rtpr0;

  @Column(name = "xti_rtpr1")
  private String xti_rtpr1;

  @Column(name = "cod_family")
  private String cod_family;

  @Column(name = "cod_grupo")
  private String cod_grupo;

  @Column(name = "cod_tipoproducto")
  private String cod_tipoproducto;

  @Column(name = "imp_mvnfcp")
  private Double imp_mvnfcp;

  @Column(name = "imp_mvnfrv")
  private Double imp_mvnfrv;

  @Column(name = "cod_tipoperacion")
  private String cod_tipoperacion;

  @Column(name = "fec_expirydate")
  private String fec_expirydate;

  @Column(name = "fec_lastflow")
  private String fec_lastflow;

  @Column(name = "cod_status")
  private String cod_status;

  @Column(name = "fec_tetrn")
  private String fec_tetrn;

  @Column(name = "fec_initialpay")
  private String fec_initialpay;

  @Column(name = "cod_seccod")
  private String cod_seccod;

  @Column(name = "imp_nominal")
  private Double imp_nominal;

  @Column(name = "cod_strument")
  private String cod_strument;

  @Column(name = "cod_cd")
  private String cod_cd;

  @Column(name = "imp_rtacr0")
  private Double imp_rtacr0;

  @Column(name = "imp_rtacr1")
  private Double imp_rtacr1;

  @Column(name = "imp_iqty")
  private Double imp_iqty;

  @Column(name = "xti_delivsettle")
  private String xti_delivsettle;

  @Column(name = "cod_strtgy")
  private String cod_strtgy;

  @Column(name = "cod_accsct")
  private String cod_accsct;

  @Column(name = "cod_operaorigen")
  private String cod_operaorigen;

  @Column(name = "cod_lastmrktoper")
  private String cod_lastmrktoper;

  @Column(name = "fec_lastmrktoper")
  private String fec_lastmrktoper;

  @Column(name = "cod_dtefst")
  private String cod_dtefst;

  @Column(name = "cod_valstat")
  private String cod_valstat;

  @Column(name = "cod_entity")
  private String cod_entity;

  @Column(name = "imp_volatility")
  private Double imp_volatility;

  @Column(name = "imp_forwardpago")
  private Double imp_forwardpago;

  @Column(name = "imp_pot")
  private Double imp_pot;

  @Column(name = "imp_nomientrfs")
  private Double imp_nomientrfs;

  @Column(name = "imp_nomientrfx")
  private Double imp_nomientrfx;

  @Column(name = "imp_nomisalidafs")
  private Double imp_nomisalidafs;

  @Column(name = "imp_nomisalidafx")
  private Double imp_nomisalidafx;

  @Column(name = "imp_fmv")
  private Double imp_fmv;

  @Column(name = "imp_nonfmv")
  private Double imp_nonfmv;

  @Column(name = "cod_divisanomi")
  private String cod_divisanomi;

  @Column(name = "imp_tipocambio")
  private Double imp_tipocambio;

  @Column(name = "cod_diventradafx")
  private String cod_diventradafx;

  @Column(name = "cod_divsalidafx")
  private String cod_divsalidafx;

  @Column(name = "cod_csatype")
  private String cod_csatype;

  @Column(name = "cod_nomvivopago")
  private String cod_nomvivopago;

  @Column(name = "cod_nomvivorecibo")
  private String cod_nomvivorecibo;

  @Column(name = "fec_nextfixrecibo")
  private String fec_nextfixrecibo;

  @Column(name = "fec_nextfixpago")
  private String fec_nextfixpago;

  @Column(name = "cod_nextfixpatarec")
  private String cod_nextfixpatarec;

  @Column(name = "cod_nextfixpatapago")
  private String cod_nextfixpatapago;

  @Column(name = "cod_m_collagcat")
  private String cod_m_collagcat;

  @Column(name = "imp_pl_csnfcp2")
  private Double imp_pl_csnfcp2;

  @Column(name = "imp_pl_csnfrv2")
  private Double imp_pl_csnfrv2;

  @Column(name = "imp_pl_rvu2")
  private Double imp_pl_rvu2;

  @Column(name = "cod_pl_inscur")
  private String cod_pl_inscur;

  @Column(name = "cod_vegaquot")
  private String cod_vegaquot;

  @Column(name = "imp_total_ffc")
  private Double imp_total_ffc;

  @Column(name = "cod_tp_dtefxgf")
  private String cod_tp_dtefxgf;

  @Column(name = "cod_tp_dtefxgl")
  private String cod_tp_dtefxgl;

  @Column(name = "cod_tp_dtefst")
  private String cod_tp_dtefst;

  @Column(name = "cod_tp_dtelst")
  private String cod_tp_dtelst;

  @Column(name = "imp_tp_rtamc00")
  private Double imp_tp_rtamc00;

  @Column(name = "imp_tp_rtamc10")
  private Double imp_tp_rtamc10;

  @Column(name = "cod_tp_rtdpn00")
  private String cod_tp_rtdpn00;

  @Column(name = "cod_tp_rtdpn10")
  private String cod_tp_rtdpn10;

  @Column(name = "cod_tp_rtdxc00")
  private String cod_tp_rtdxc00;

  @Column(name = "cod_tp_rtdxc10")
  private String cod_tp_rtdxc10;

  @Column(name = "cod_tp_rtdxg00")
  private String cod_tp_rtdxg00;

  @Column(name = "cod_tp_rtdxg10")
  private String cod_tp_rtdxg10;

  @Column(name = "imp_tp_rtfxc00")
  private Double imp_tp_rtfxc00;

  @Column(name = "imp_tp_rtfxc10")
  private Double imp_tp_rtfxc10;

  @Column(name = "cod_model")
  private String cod_model;

  @Column(name = "fec_dtesys")
  private String fec_dtesys;

  @Column(name = "cod_tp_nomcur")
  private String cod_tp_nomcur;

  @Column(name = "cod_divpago")
  private String cod_divpago;

  @Column(name = "cod_divrecibo")
  private String cod_divrecibo;

  @Column(name = "xti_compraventa")
  private String xti_compraventa;

  @Column(name = "fec_ultfixpago")
  private String fec_ultfixpago;

  @Column(name = "fec_ultfixrecibo")
  private String fec_ultfixrecibo;

  @Column(name = "imp_mvpago")
  private Double imp_mvpago;

  @Column(name = "imp_mvrecibo")
  private Double imp_mvrecibo;

  @Column(name = "imp_mvcontrasala")
  private Double imp_mvcontrasala;

  @Column(name = "imp_mvcontraeur")
  private Double imp_mvcontraeur;

  @Column(name = "imp_plpago")
  private Double imp_plpago;

  @Column(name = "imp_plrecibo")
  private Double imp_plrecibo;

  @Column(name = "imp_plcontrasala")
  private Double imp_plcontrasala;

  @Column(name = "imp_plcontraeur")
  private Double imp_plcontraeur;

  @Column(name = "cod_aplicacionfront")
  private String cod_aplicacionfront;

  @Column(name = "ind_adj")
  private String ind_adj;

  @Column(name = "des_ficorig")
  private String des_ficorig;

  @Column(name = "xti_murex")
  private String xti_murex;

  @Column(name = "cod_region")
  private String cod_region;

  public Murex() {
  }

  public Murex(Long id, String fec_proceso, String cod_nominalpago, String cod_nominalrecib, Double imp_npvpago,
               Double imp_npvrec, String cod_cur_pl, String cod_numoperfront, Double imp_irdfpv1, Double imp_irdfpv2,
               Double imp_csfi, Double imp_csnf, Double imp_fe, Double imp_fefi, Double imp_fpnfcp, Double imp_fpnfrv,
               Double imp_ftfi, Double imp_delta, Double imp_gamma, Double imp_rho, Double imp_theta, Double imp_vega,
               Double imp_zcsnv, String cod_counterpart, String cod_cntrplb, String xti_isinternal, Double imp_lqty,
               String cod_portfolio, Double imp_rtccp0, Double imp_rtccp1, String xti_rtpr0, String xti_rtpr1, String cod_family,
               String cod_grupo, String cod_tipoproducto, Double imp_mvnfcp, Double imp_mvnfrv, String cod_tipoperacion,
               String fec_expirydate, String fec_lastflow, String cod_status, String fec_tetrn, String fec_initialpay,
               String cod_seccod, Double imp_nominal, String cod_strument, String cod_cd, Double imp_rtacr0,
               Double imp_rtacr1, Double imp_iqty, String xti_delivsettle, String cod_strtgy, String cod_accsct,
               String cod_operaorigen, String cod_lastmrktoper, String fec_lastmrktoper, String cod_dtefst, String cod_valstat,
               String cod_entity, Double imp_volatility, Double imp_forwardpago, Double imp_pot, Double imp_nomientrfs,
               Double imp_nomientrfx, Double imp_nomisalidafs, Double imp_nomisalidafx, Double imp_fmv, Double imp_nonfmv,
               String cod_divisanomi, Double imp_tipocambio, String cod_diventradafx, String cod_divsalidafx, String cod_csatype,
               String cod_nomvivopago, String cod_nomvivorecibo, String fec_nextfixrecibo, String fec_nextfixpago, String cod_nextfixpatarec,
               String cod_nextfixpatapago, String cod_m_collagcat, Double imp_pl_csnfcp2, Double imp_pl_csnfrv2, Double imp_pl_rvu2,
               String cod_pl_inscur, String cod_vegaquot, Double imp_total_ffc, String cod_tp_dtefxgf, String cod_tp_dtefxgl,
               String cod_tp_dtefst, String cod_tp_dtelst, Double imp_tp_rtamc00, Double imp_tp_rtamc10, String cod_tp_rtdpn00,
               String cod_tp_rtdpn10, String cod_tp_rtdxc00, String cod_tp_rtdxc10, String cod_tp_rtdxg00, String cod_tp_rtdxg10,
               Double imp_tp_rtfxc00, Double imp_tp_rtfxc10, String cod_model, String fec_dtesys, String cod_tp_nomcur, String cod_divpago,
               String cod_divrecibo, String xti_compraventa, String fec_ultfixpago, String fec_ultfixrecibo, Double imp_mvpago,
               Double imp_mvrecibo, Double imp_mvcontrasala, Double imp_mvcontraeur, Double imp_plpago, Double imp_plrecibo, Double imp_plcontrasala,
               Double imp_plcontraeur, String cod_aplicacionfront, String ind_adj, String des_ficorig, String xti_murex, String cod_region) {
    this.id = id;
    this.fec_proceso = fec_proceso;
    this.cod_nominalpago = cod_nominalpago;
    this.cod_nominalrecib = cod_nominalrecib;
    this.imp_npvpago = imp_npvpago;
    this.imp_npvrec = imp_npvrec;
    this.cod_cur_pl = cod_cur_pl;
    this.cod_numoperfront = cod_numoperfront;
    this.imp_irdfpv1 = imp_irdfpv1;
    this.imp_irdfpv2 = imp_irdfpv2;
    this.imp_csfi = imp_csfi;
    this.imp_csnf = imp_csnf;
    this.imp_fe = imp_fe;
    this.imp_fefi = imp_fefi;
    this.imp_fpnfcp = imp_fpnfcp;
    this.imp_fpnfrv = imp_fpnfrv;
    this.imp_ftfi = imp_ftfi;
    this.imp_delta = imp_delta;
    this.imp_gamma = imp_gamma;
    this.imp_rho = imp_rho;
    this.imp_theta = imp_theta;
    this.imp_vega = imp_vega;
    this.imp_zcsnv = imp_zcsnv;
    this.cod_counterpart = cod_counterpart;
    this.cod_cntrplb = cod_cntrplb;
    this.xti_isinternal = xti_isinternal;
    this.imp_lqty = imp_lqty;
    this.cod_portfolio = cod_portfolio;
    this.imp_rtccp0 = imp_rtccp0;
    this.imp_rtccp1 = imp_rtccp1;
    this.xti_rtpr0 = xti_rtpr0;
    this.xti_rtpr1 = xti_rtpr1;
    this.cod_family = cod_family;
    this.cod_grupo = cod_grupo;
    this.cod_tipoproducto = cod_tipoproducto;
    this.imp_mvnfcp = imp_mvnfcp;
    this.imp_mvnfrv = imp_mvnfrv;
    this.cod_tipoperacion = cod_tipoperacion;
    this.fec_expirydate = fec_expirydate;
    this.fec_lastflow = fec_lastflow;
    this.cod_status = cod_status;
    this.fec_tetrn = fec_tetrn;
    this.fec_initialpay = fec_initialpay;
    this.cod_seccod = cod_seccod;
    this.imp_nominal = imp_nominal;
    this.cod_strument = cod_strument;
    this.cod_cd = cod_cd;
    this.imp_rtacr0 = imp_rtacr0;
    this.imp_rtacr1 = imp_rtacr1;
    this.imp_iqty = imp_iqty;
    this.xti_delivsettle = xti_delivsettle;
    this.cod_strtgy = cod_strtgy;
    this.cod_accsct = cod_accsct;
    this.cod_operaorigen = cod_operaorigen;
    this.cod_lastmrktoper = cod_lastmrktoper;
    this.fec_lastmrktoper = fec_lastmrktoper;
    this.cod_dtefst = cod_dtefst;
    this.cod_valstat = cod_valstat;
    this.cod_entity = cod_entity;
    this.imp_volatility = imp_volatility;
    this.imp_forwardpago = imp_forwardpago;
    this.imp_pot = imp_pot;
    this.imp_nomientrfs = imp_nomientrfs;
    this.imp_nomientrfx = imp_nomientrfx;
    this.imp_nomisalidafs = imp_nomisalidafs;
    this.imp_nomisalidafx = imp_nomisalidafx;
    this.imp_fmv = imp_fmv;
    this.imp_nonfmv = imp_nonfmv;
    this.cod_divisanomi = cod_divisanomi;
    this.imp_tipocambio = imp_tipocambio;
    this.cod_diventradafx = cod_diventradafx;
    this.cod_divsalidafx = cod_divsalidafx;
    this.cod_csatype = cod_csatype;
    this.cod_nomvivopago = cod_nomvivopago;
    this.cod_nomvivorecibo = cod_nomvivorecibo;
    this.fec_nextfixrecibo = fec_nextfixrecibo;
    this.fec_nextfixpago = fec_nextfixpago;
    this.cod_nextfixpatarec = cod_nextfixpatarec;
    this.cod_nextfixpatapago = cod_nextfixpatapago;
    this.cod_m_collagcat = cod_m_collagcat;
    this.imp_pl_csnfcp2 = imp_pl_csnfcp2;
    this.imp_pl_csnfrv2 = imp_pl_csnfrv2;
    this.imp_pl_rvu2 = imp_pl_rvu2;
    this.cod_pl_inscur = cod_pl_inscur;
    this.cod_vegaquot = cod_vegaquot;
    this.imp_total_ffc = imp_total_ffc;
    this.cod_tp_dtefxgf = cod_tp_dtefxgf;
    this.cod_tp_dtefxgl = cod_tp_dtefxgl;
    this.cod_tp_dtefst = cod_tp_dtefst;
    this.cod_tp_dtelst = cod_tp_dtelst;
    this.imp_tp_rtamc00 = imp_tp_rtamc00;
    this.imp_tp_rtamc10 = imp_tp_rtamc10;
    this.cod_tp_rtdpn00 = cod_tp_rtdpn00;
    this.cod_tp_rtdpn10 = cod_tp_rtdpn10;
    this.cod_tp_rtdxc00 = cod_tp_rtdxc00;
    this.cod_tp_rtdxc10 = cod_tp_rtdxc10;
    this.cod_tp_rtdxg00 = cod_tp_rtdxg00;
    this.cod_tp_rtdxg10 = cod_tp_rtdxg10;
    this.imp_tp_rtfxc00 = imp_tp_rtfxc00;
    this.imp_tp_rtfxc10 = imp_tp_rtfxc10;
    this.cod_model = cod_model;
    this.fec_dtesys = fec_dtesys;
    this.cod_tp_nomcur = cod_tp_nomcur;
    this.cod_divpago = cod_divpago;
    this.cod_divrecibo = cod_divrecibo;
    this.xti_compraventa = xti_compraventa;
    this.fec_ultfixpago = fec_ultfixpago;
    this.fec_ultfixrecibo = fec_ultfixrecibo;
    this.imp_mvpago = imp_mvpago;
    this.imp_mvrecibo = imp_mvrecibo;
    this.imp_mvcontrasala = imp_mvcontrasala;
    this.imp_mvcontraeur = imp_mvcontraeur;
    this.imp_plpago = imp_plpago;
    this.imp_plrecibo = imp_plrecibo;
    this.imp_plcontrasala = imp_plcontrasala;
    this.imp_plcontraeur = imp_plcontraeur;
    this.cod_aplicacionfront = cod_aplicacionfront;
    this.ind_adj = ind_adj;
    this.des_ficorig = des_ficorig;
    this.xti_murex = xti_murex;
    this.cod_region = cod_region;
  }


}