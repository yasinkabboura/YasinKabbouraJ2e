package ma.enset.spring_mvc.web;

import lombok.AllArgsConstructor;
import ma.enset.spring_mvc.entities.Patient;
import ma.enset.spring_mvc.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller @AllArgsConstructor
public class PatientController {
    PatientRepository patientRepository;
    @GetMapping(path = "/user/index")
    public String patient(Model model,
                          @RequestParam(name = "page",defaultValue = "0") int page,
                          @RequestParam(name = "size",defaultValue = "5")int size,
                          @RequestParam(name = "keyword",defaultValue = "")String keyword){
        Page<Patient> l = patientRepository.findByNomContains(keyword,PageRequest.of(page,size));
        model.addAttribute("listPatients",l.getContent());
        model.addAttribute("pages",new int[l.getTotalPages()]);
        model.addAttribute("current",page);
        model.addAttribute("keyword",keyword);
        return "patients";
    }
    @GetMapping( "/admin/delete")
    public String delete(Long id,String keyword,int page){
        patientRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping( "/")
    public String home(){
        return "home";
    }
    @GetMapping( "/admin/formPatients")
    public String formPatients (Model model){
        model.addAttribute("patient", new Patient());
        return "formPatients";
    }
    @PostMapping(path ="/admin/save")
    public String save (Model model, @Valid Patient patient, BindingResult bindingResult,
                        @RequestParam(defaultValue = "") String keyword,
                        @RequestParam(defaultValue = "0")int page) {
        if (bindingResult.hasErrors()) return "formPatients" ;
        patientRepository.save (patient);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping( "/admin/editPatient")
    public String editPatient (Model model,Long id,String keyword,int page){
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient==null){
            throw new RuntimeException("Patient dosnt exist");
        }
        model.addAttribute("patient", patient);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "editPatient";
    }
}
