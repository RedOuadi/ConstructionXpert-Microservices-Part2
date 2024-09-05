package tacheservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tacheservice.client.ProjetClient;
import tacheservice.model.Status;
import tacheservice.exception.TacheNotFoundException;
import tacheservice.model.Tache;
import tacheservice.repository.TacheRepository;

import java.util.List;

@Service
public class TacheService {

    @Autowired
    private TacheRepository tacheRepository;

    @Autowired
    private ProjetClient projetClient;

    public void addTache(Tache tache, int idProjet){

        try {
            projetClient.getProjetby(idProjet); // Utilisation du client Feign
        } catch (Exception e) {
            throw new IllegalArgumentException("Projet non trouvé : " + idProjet);
        }

        tache.setIdProjet(idProjet);
        tache.setStatus(Status.A_FAIRE);
        tacheRepository.save(tache);
    }

    public Tache getTache(Long idTache) {

        Tache tache = tacheRepository
                .findById(idTache)
                .orElseThrow(TacheNotFoundException::new);
        return tache;
    }

    public List<Tache> getAllTaches() {
        return tacheRepository.findAll();
    }

    public List<Tache> getTachesByProjet(int idProjet){
        return tacheRepository.findByIdProjet(idProjet);
    }

    public void updateTache(int idtache, Tache tache) {
        tacheRepository
                .findById((long) idtache)
                .orElseThrow(TacheNotFoundException::new);

        tache.setIdProjet(idtache);

        tacheRepository.save(tache);
    }

    public void deleteTache(Long idtache) {
        Tache tacheSupprime = tacheRepository
                .findById(idtache)
                .orElseThrow(TacheNotFoundException::new);

        tacheRepository.delete(tacheSupprime);
    }
}
