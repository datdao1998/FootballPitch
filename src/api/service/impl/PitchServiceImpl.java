package api.service.impl;

import api.model.dao.PitchDAO;
import api.model.dao.impl.PitchDAOImpl;
import api.model.entity.BookingBill;
import api.model.entity.Pitch;
import api.model.entity.Type;
import api.service.BookingBillService;
import api.service.PitchService;
import api.service.TypeService;
import common.exception.FPException;
import common.util.FPUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class PitchServiceImpl implements PitchService {

    private PitchDAO pitchDAO = new PitchDAOImpl();

    @Override
    public Optional<Pitch> create(Pitch pitch) throws FPException {
        return  pitchDAO.save(pitch);
    }

    @Override
    public Optional<Pitch> findById(Integer id) throws FPException {
        Optional<Pitch> opt = pitchDAO.findById(id);
        if(!opt.isPresent()) throw new FPException.NotFoundEntityException();
        return opt;

    }

    @Override
    public Optional<Pitch> update(Integer id, Pitch pitch) throws FPException {
        Optional<Pitch> opt = pitchDAO.findById(id);
        if (!opt.isPresent()) throw new FPException.NotFoundEntityException();
        pitch.setId(id);
        return pitchDAO.save(pitch);
    }

    @Override
    public List<Pitch> search(String name, String description, String numberOfPlyer) {
        TypeService typeService = new TypeServiceImpl();
        ArrayList<Pitch> pitches = new ArrayList<>();
        if(numberOfPlyer!=null && !numberOfPlyer.isEmpty()){
            LinkedList<Type> types = (LinkedList<Type>) typeService.search(Integer.parseInt(numberOfPlyer));
            for(int i = 0 ; i < types.size();i++){
                ArrayList<Pitch> element = (ArrayList<Pitch>) pitchDAO.search(name,description,types.get(i).getId()+"");
                pitches.addAll(element);
            }
            return pitches;
        }
        else{
            return pitchDAO.search(name,description,null);
        }

    }

    @Override
    public List<Pitch> searchAvailablePitch(String timeIn, String numberOfPlayer) throws FPException.DateFormatException {
        BookingBillService bookingBillService = new BookingBillServiceImpl();
        ArrayList<Pitch> pitches = (ArrayList<Pitch>) search(null, null, numberOfPlayer);
        if(timeIn!=null && !timeIn.isEmpty()) {
            if (!FPUtils.validateTimeDateFormat(timeIn)) throw new FPException.DateFormatException();
            timeIn = timeIn.trim();
            String timeToSearch = timeIn.substring(9, timeIn.length());
            Integer test = Integer.parseInt(timeIn.substring(0, 8).replaceAll(":", ""));
            ArrayList<BookingBill> bookingBillStatus1 = (ArrayList<BookingBill>) bookingBillService.search(timeToSearch, "BOOKED", null, null, null);
            ArrayList<BookingBill> bookingBillStatus2 = (ArrayList<BookingBill>) bookingBillService.search(timeToSearch, "CHECKED_IN", null, null, null);
            bookingBillStatus1.addAll(bookingBillStatus2);
            bookingBillStatus1.removeIf(i -> {
                Integer t1 = Integer.parseInt(i.getTimeIn().trim().substring(0, 8).replaceAll(":", ""));
                Integer t2 = Integer.parseInt(i.getTimeOut().trim().substring(0, 8).replaceAll(":", ""));
                if ( (t1 <= test && test < t2) || (t1 < test+15000 && test+15000 <= t2)) {
                    return false;
                }
                return true;
            });
            pitches.removeIf(pitch -> {
                for (int i = 0; i < bookingBillStatus1.size(); i++) {
                    if (pitch.getId() == bookingBillStatus1.get(i).getPitch().getId())
                        return true;
                }
                return false;
            });
            return pitches;
        }
        else{
            return pitches;
        }
    }


}
