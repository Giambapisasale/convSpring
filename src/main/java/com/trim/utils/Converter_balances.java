package com.trim.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

import com.trim.schema.CBIBalanceType12;
import com.trim.schema.CBIBalanceType12Code;
import com.trim.schema.CBIBalanceType5Choice;
import com.trim.schema.CBICashBalance3;

@SuppressWarnings("rawtypes")
public class Converter_balances extends DozerConverter<List, List>implements MapperAware {

	public Converter_balances() {
		super(List.class, List.class);
	}

	private Mapper mapper;

	@SuppressWarnings("unchecked")
	@Override
	public List<CBICashBalance3> convertFrom(List source, List destination) {
		List<CBICashBalance3> originalToMapped = null;
		if (destination == null) {
			originalToMapped = new ArrayList<CBICashBalance3>();
		} else {
			originalToMapped = (List<CBICashBalance3>) destination;
		}
		for (Properties item : (List<Properties>) source) {
			String type = item.getProperty("type");
			if (type != null) {
				// TODO controllare esistenza regole di mapping e possibilmente
				// generalizzare se esiste un getter di tutte le regole con ID
				// presenti
				if (type.equals("61")) {
					CBICashBalance3 mappedItem = mapper.map(item, CBICashBalance3.class, "map61_balance");
					mappedItem.setTp(createBalType(CBIBalanceType12Code.OPBD));
					originalToMapped.add(mappedItem);
				} else if (type.equals("64")) {
					CBICashBalance3 mappedItem = mapper.map(item, CBICashBalance3.class, "map64_balance_1");
					mappedItem.setTp(createBalType(CBIBalanceType12Code.CLBD));
					originalToMapped.add(mappedItem);
					mappedItem = mapper.map(item, CBICashBalance3.class, "map64_balance_2");
					mappedItem.setTp(createBalType(CBIBalanceType12Code.CLAV));
					originalToMapped.add(mappedItem);
				} else if (type.equals("65")) {
					CBICashBalance3 mappedItem = mapper.map(item, CBICashBalance3.class, "map65_balance_1");
					mappedItem.setTp(createBalType(CBIBalanceType12Code.FWAV));
					originalToMapped.add(mappedItem);
					mappedItem = mapper.map(item, CBICashBalance3.class, "map65_balance_2");
					mappedItem.setTp(createBalType(CBIBalanceType12Code.FWAV));
					originalToMapped.add(mappedItem);
					mappedItem = mapper.map(item, CBICashBalance3.class, "map65_balance_3");
					mappedItem.setTp(createBalType(CBIBalanceType12Code.FWAV));
					originalToMapped.add(mappedItem);
					mappedItem = mapper.map(item, CBICashBalance3.class, "map65_balance_4");
					mappedItem.setTp(createBalType(CBIBalanceType12Code.FWAV));
					originalToMapped.add(mappedItem);
					mappedItem = mapper.map(item, CBICashBalance3.class, "map65_balance_5");
					mappedItem.setTp(createBalType(CBIBalanceType12Code.FWAV));
					originalToMapped.add(mappedItem);
				}

			}

		}
		return originalToMapped;
	}

	private CBIBalanceType12 createBalType(CBIBalanceType12Code balType) {
		CBIBalanceType12 value = new CBIBalanceType12();
		CBIBalanceType5Choice valuet5 = new CBIBalanceType5Choice();
		valuet5.setCd(balType);
		value.setCdOrPrtry(valuet5);
		return value;
	}

	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<Properties> convertTo(List source, List destination) {
		return null;
	}

}