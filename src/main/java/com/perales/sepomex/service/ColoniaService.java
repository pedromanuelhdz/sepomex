package com.perales.sepomex.service;

import com.perales.sepomex.contract.ServiceGeneric;
import com.perales.sepomex.model.*;
import com.perales.sepomex.repository.ColoniaRepository;
import com.perales.util.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Transactional
@Service
public class ColoniaService implements ServiceGeneric<Colonia, Integer> {

  @Autowired
  private ColoniaRepository coloniaRepository;

  @Autowired
  private MunicipioService municipioService;

  @Autowired
  private CiudadService ciudadService;

  @Autowired
  private EstadoService estadoService;

  @Autowired
  private AsentamientoTipoService asentamientoTipoService;

  @Autowired
  private ZonaTipoService zonaTipoService;


  private static final String FILE_NAME = "/tmp/sepomex.txt";
  private static final int POSICIONES_MAXIMAS_SEPARADOR = 15;

  public Colonia buscarPorId(Integer id) {
    return coloniaRepository.getOne(id);
  }

  public List<Colonia> buscarTodos(int page, int size) {
    return null;
  }

  public Colonia guardar(Colonia entity) {
    return coloniaRepository.save(entity);
  }

  public Colonia actualizar(Colonia entity) {
    return null;
  }

  public Colonia borrar(Integer id) {
    return null;
  }

  public Boolean cargaMasiva() throws IOException {
    Parser parser = new Parser();
    List<String> strings = Files.readAllLines(Paths.get(FILE_NAME), Charset.forName("UTF-8"));
    Integer contador = 0;
    for (String s : strings) {
      contador++;
      List<String> list = Arrays.asList(s.split("\\|"));
      if (list.size() == POSICIONES_MAXIMAS_SEPARADOR) {
        Colonia colonia = parser.convertirListaColonia(list);
        revisarColonia(colonia);
        System.out.println(colonia);
      }
    }
    System.out.println(contador);
    return true;
  }

  private void revisarColonia(Colonia colonia){
      AsentamientoTipo asentamientoTipo = asentamientoTipoService.findBySepomexClave(colonia.getAsentamientoTipo().getSepomexClave());
      if(asentamientoTipo == null){
          asentamientoTipo = asentamientoTipoService.guardar(colonia.getAsentamientoTipo());
          colonia.setAsentamientoTipo(asentamientoTipo);
          System.out.println("******************************");
          System.out.println(asentamientoTipo);
          System.out.println("******************************");
      }else{
          System.out.println("REUTILIZANDO");
          colonia.setAsentamientoTipo(asentamientoTipo);
      }

      Ciudad ciudad = ciudadService.findByClave(colonia.getCiudad().getClave());
      if(ciudad == null){
          ciudad = ciudadService.guardar(colonia.getCiudad());
          colonia.setCiudad(ciudad);
          System.out.println("******************************");
          System.out.println(ciudad);
          System.out.println("******************************");
      }else{
          System.out.println("REUTILIZANDO");
          colonia.setCiudad(ciudad);
      }

      Estado estado = estadoService.findByInegiClave(colonia.getEstado().getInegiClave());
      if(estado == null){
          estado = estadoService.guardar(colonia.getEstado());
          colonia.setEstado(estado);
          System.out.println("******************************");
          System.out.println(estado);
          System.out.println("******************************");
      }else{
          System.out.println("REUTILIZANDO");
          colonia.setEstado(estado);
      }

      Municipio municipio = municipioService.findByInegiClave(colonia.getMunicipio().getInegiClave());
      if(municipio == null){
          municipio = municipioService.guardar(colonia.getMunicipio());
          colonia.setMunicipio(municipio);
          System.out.println("******************************");
          System.out.println(municipio);
          System.out.println("******************************");
      }else{
          System.out.println("REUTILIZANDO");
          colonia.setMunicipio(municipio);
      }

      ZonaTipo zonaTipo = zonaTipoService.findByNombre(colonia.getZonaTipo().getNombre());
      if(zonaTipo == null){
          zonaTipo = zonaTipoService.guardar(colonia.getZonaTipo());
          colonia.setZonaTipo(zonaTipo);
          System.out.println("******************************");
          System.out.println(zonaTipo);
          System.out.println("******************************");
      }else{
          System.out.println("REUTILIZANDO");
          colonia.setZonaTipo(zonaTipo);
      }
      guardar(colonia);

  }

}
