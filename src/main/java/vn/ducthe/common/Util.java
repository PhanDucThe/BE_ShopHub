package vn.ducthe.common;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import vn.ducthe.exception.ResourceNotFoundException;
import vn.ducthe.model.AttributeEntity;
import vn.ducthe.model.AttributeOptionEntity;
import vn.ducthe.model.CategoryAttributeEntity;
import vn.ducthe.repository.AttributeOptionRepository;
import vn.ducthe.repository.AttributeRepository;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class Util {

    private final AttributeOptionRepository  attributeOptionRepository;
    private final AttributeRepository attributeRepository;

    public String toSlugify(String input) {
        if (input == null) return "";
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String withoutAccents = pattern.matcher(normalized).replaceAll("");
        return StringUtils.stripAccents(withoutAccents)
                .toLowerCase(Locale.ENGLISH)
                .replaceAll("[^a-z0-9-]", "-")
                .replaceAll("-+", "-")
                .replaceAll("^-|-$", "");
    }

    public String getValueFromSlug(Long attributeId, String slug) {
        List<AttributeOptionEntity> attributeOption = attributeOptionRepository
                .findByAttributeEntity_Id(attributeId);
        for (AttributeOptionEntity attributeOptionEntity : attributeOption) {
            if (attributeOptionEntity.getSlug().equals(slug)) {
                return attributeOptionEntity.getValue();
            }
        }
        throw new ResourceNotFoundException("Value not found for slug");
    }

        public String getSignatureFromOptions(Map<String, String> options, List<CategoryAttributeEntity> categoryAttributeEntities) {
            List<String> optionIds = new ArrayList<>();
            categoryAttributeEntities.forEach(attr -> {
                // find Attribute
                AttributeEntity attribute = attributeRepository.findById(attr.getAttributeEntity().getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Attribute not found"));
                // Giá trị tương ứng.
                String value = options.get(toSlugify(attribute.getNameEn())); // color.
                AttributeOptionEntity attributeOptionEntity = attributeOptionRepository
                        .findByAttributeEntity_IdAndValue(attribute.getId(), getValueFromSlug(attribute.getId(), value))
                        .orElseThrow(() -> new ResourceNotFoundException("AttributeOption not found"));
                optionIds.add(String.valueOf(attributeOptionEntity.getId()));
            });
            return String.join("_", optionIds);
        }
}
