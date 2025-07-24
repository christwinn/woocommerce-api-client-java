/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.pl_wtx_woocommerce.model.tax;

public class ISO3166 {

    public ISO3166(){}
    /**
     * Correct at 2025-07-11 as per <a href="https://en.wikipedia.org/wiki/ISO_3166-2">https://en.wikipedia.org/wiki/ISO_3166-2</a>
     * Apologies to countries with accents, apostrophes, etc, this is as close as we can get to your country name.
     *
     */
    public enum CountryEnum {
        ANDORRA("AD"),
        UNITED_ARAB_EMIRATES("AE"),
        AFGHANISTAN("AF"),
        ANTIGUA_AND_BARBUDA("AG"),
        ANGUILLA("AI"),
        ALBANIA("AL"),
        ARMENIA("AM"),
        ANGOLA("AO"),
        ANTARCTICA("AQ"),
        ARGENTINA("AR"),
        AMERICAN_SAMOA("AS"),
        AUSTRIA("AT"),
        AUSTRALIA("AU"),
        ARUBA("AW"),
        ALAND_ISLANDS("AX"),
        AZERBAIJAN("AZ"),
        BOSNIA_AND_HERZEGOVINA("BA"),
        BARBADOS("BB"),
        BANGLADESH("BD"),
        BELGIUM("BE"),
        BURKINA_FASO("BF"),
        BULGARIA("BG"),
        BAHRAIN("BH"),
        BURUNDI("BI"),
        BENIN("BJ"),
        SAINT_BARTHELEMY("BL"),
        BERMUDA("BM"),
        BRUNEI_DARUSSALAM("BN"),
        BOLIVIA_PLURINATIONAL_STATE_OF("BO"),
        BONAIRE_SINT_EUSTATIUS_AND_SABA("BQ"),
        BRAZIL("BR"),
        BAHAMAS("BS"),
        BHUTAN("BT"),
        BOUVET_ISLAND("BV"),
        BOTSWANA("BW"),
        BELARUS("BY"),
        BELIZE("BZ"),
        CANADA("CA"),
        COCOS_KEELING_ISLANDS("CC"),

        CONGO_DEMOCRATIC_REPUBLIC_OF_THE("CD"),

        CENTRAL_AFRICAN_REPUBLIC("CF"),

        CONGO("CG"),

        SWITZERLAND("CH"),

        COTE_D_IVOIRE("CI"),

        COOK_ISLANDS("CK"),

        CHILE("CL"),

        CAMEROON("CM"),

        CHINA("CN"),

        COLOMBIA("CO"),

        COSTA_RICA("CR"),

        CUBA("CU"),

        CABO_VERDE("CV"),

        CURACAO("CW"),

        CHRISTMAS_ISLAND("CX"),

        CYPRUS("CY"),

        CZECHIA("CZ"),

        GERMANY("DE"),

        DJIBOUTI("DJ"),

        DENMARK("DK"),

        DOMINICA("DM"),

        DOMINICAN_REPUBLIC("DO"),

        ALGERIA("DZ"),

        ECUADOR("EC"),

        ESTONIA("EE"),

        EGYPT("EG"),

        WESTERN_SAHARA("EH"),

        ERITREA("ER"),

        SPAIN("ES"),

        ETHIOPIA("ET"),

        FINLAND("FI"),

        FIJI("FJ"),

        FALKLAND_ISLANDS_MALVINAS("FK"),

        MICRONESIA_FEDERATED_STATES_OF("FM"),

        FAROE_ISLANDS("FO"),

        FRANCE("FR"),

        GABON("GA"),

        UNITED_KINGDOM_OF_GREAT_BRITAIN_AND_NORTHERN_IRELAND("GB"),

        GRENADA("GD"),

        GEORGIA("GE"),

        FRENCH_GUIANA("GF"),

        GUERNSEY("GG"),

        GHANA("GH"),

        GIBRALTAR("GI"),

        GREENLAND("GL"),

        GAMBIA("GM"),

        GUINEA("GN"),

        GUADELOUPE("GP"),

        EQUATORIAL_GUINEA("GQ"),

        GREECE("GR"),

        SOUTH_GEORGIA_AND_THE_SOUTH_SANDWICH_ISLANDS("GS"),

        GUATEMALA("GT"),

        GUAM("GU"),

        GUINEA_BISSAU("GW"),

        GUYANA("GY"),

        HONG_KONG("HK"),

        HEARD_ISLAND_AND_MCDONALD_ISLANDS("HM"),

        HONDURAS("HN"),

        CROATIA("HR"),

        HAITI("HT"),

        HUNGARY("HU"),

        INDONESIA("ID"),

        IRELAND("IE"),

        ISRAEL("IL"),

        ISLE_OF_MAN("IM"),

        INDIA("IN"),

        BRITISH_INDIAN_OCEAN_TERRITORY("IO"),

        IRAQ("IQ"),

        IRAN__ISLAMIC_REPUBLIC_OF("IR"),

        ICELAND("IS"),

        ITALY("IT"),

        JERSEY("JE"),

        JAMAICA("JM"),

        JORDAN("JO"),

        JAPAN("JP"),

        KENYA("KE"),

        KYRGYZSTAN("KG"),

        CAMBODIA("KH"),

        KIRIBATI("KI"),

        COMOROS("KM"),

        SAINT_KITTS_AND_NEVIS("KN"),

        KOREA_DEMOCRATIC_PEOPLES_REPUBLIC_OF("KP"),

        KOREA_REPUBLIC_OF("KR"),

        KUWAIT("KW"),

        CAYMAN_ISLANDS("KY"),

        KAZAKHSTAN("KZ"),

        LAO_PEOPLES_DEMOCRATIC_REPUBLIC("LA"),

        LEBANON("LB"),

        SAINT_LUCIA("LC"),

        LIECHTENSTEIN("LI"),

        SRI_LANKA("LK"),

        LIBERIA("LR"),

        LESOTHO("LS"),

        LITHUANIA("LT"),

        LUXEMBOURG("LU"),

        LATVIA("LV"),

        LIBYA("LY"),

        MOROCCO("MA"),

        MONACO("MC"),

        MOLDOVA__REPUBLIC_OF("MD"),

        MONTENEGRO("ME"),

        SAINT_MARTIN_FRENCH_PART("MF"),

        MADAGASCAR("MG"),

        MARSHALL_ISLANDS("MH"),

        NORTH_MACEDONIA("MK"),

        MALI("ML"),

        MYANMAR("MM"),

        MONGOLIA("MN"),

        MACAO("MO"),

        NORTHERN_MARIANA_ISLANDS("MP"),

        MARTINIQUE("MQ"),

        MAURITANIA("MR"),

        MONTSERRAT("MS"),

        MALTA("MT"),

        MAURITIUS("MU"),

        MALDIVES("MV"),

        MALAWI("MW"),

        MEXICO("MX"),

        MALAYSIA("MY"),

        MOZAMBIQUE("MZ"),

        NAMIBIA("NA"),

        NEW_CALEDONIA("NC"),

        NIGER("NE"),

        NORFOLK_ISLAND("NF"),

        NIGERIA("NG"),

        NICARAGUA("NI"),

        NETHERLANDS__KINGDOM_OF_THE("NL"),

        NORWAY("NO"),

        NEPAL("NP"),

        NAURU("NR"),

        NIUE("NU"),

        NEW_ZEALAND("NZ"),

        OMAN("OM"),

        PANAMA("PA"),

        PERU("PE"),

        FRENCH_POLYNESIA("PF"),

        PAPUA_NEW_GUINEA("PG"),

        PHILIPPINES("PH"),

        PAKISTAN("PK"),

        POLAND("PL"),

        SAINT_PIERRE_AND_MIQUELON("PM"),

        PITCAIRN("PN"),

        PUERTO_RICO("PR"),

        PALESTINE__STATE_OF("PS"),

        PORTUGAL("PT"),

        PALAU("PW"),

        PARAGUAY("PY"),

        QATAR("QA"),

        REUNION("RE"),

        ROMANIA("RO"),

        SERBIA("RS"),

        RUSSIAN_FEDERATION("RU"),

        RWANDA("RW"),

        SAUDI_ARABIA("SA"),

        SOLOMON_ISLANDS("SB"),

        SEYCHELLES("SC"),

        SUDAN("SD"),

        SWEDEN("SE"),

        SINGAPORE("SG"),

        SAINT_HELENA_ASCENSION_AND_TRISTAN_DA_CUNHA("SH"),

        SLOVENIA("SI"),

        SVALBARD_AND_JAN_MAYEN("SJ"),

        SLOVAKIA("SK"),

        SIERRA_LEONE("SL"),

        SAN_MARINO("SM"),

        SENEGAL("SN"),

        SOMALIA("SO"),

        SURINAME("SR"),

        SOUTH_SUDAN("SS"),

        SAO_TOME_AND_PRINCIPE("ST"),

        EL_SALVADOR("SV"),

        SINT_MAARTEN_DUTCH_PART("SX"),

        SYRIAN_ARAB_REPUBLIC("SY"),

        ESWATINI("SZ"),

        TURKS_AND_CAICOS_ISLANDS("TC"),

        CHAD("TD"),

        FRENCH_SOUTHERN_TERRITORIES("TF"),

        TOGO("TG"),

        THAILAND("TH"),

        TAJIKISTAN("TJ"),

        TOKELAU("TK"),

        TIMOR_LESTE("TL"),

        TURKMENISTAN("TM"),

        TUNISIA("TN"),

        TONGA("TO"),

        TURKIYE("TR"),

        TRINIDAD_AND_TOBAGO("TT"),

        TUVALU("TV"),

        TAIWAN__PROVINCE_OF_CHINA("TW"),

        TANZANIA__UNITED_REPUBLIC_OF("TZ"),

        UKRAINE("UA"),

        UGANDA("UG"),

        UNITED_STATES_MINOR_OUTLYING_ISLANDS("UM"),

        UNITED_STATES_OF_AMERICA("US"),

        URUGUAY("UY"),

        UZBEKISTAN("UZ"),

        HOLY_SEE("VA"),

        SAINT_VINCENT_AND_THE_GRENADINES("VC"),

        VENEZUELA_BOLIVARIAN_REPUBLIC_OF("VE"),

        VIRGIN_ISLANDS_BRITISH("VG"),

        VIRGIN_ISLANDS_US("VI"),

        VIETNAM("VN"),

        VANUATU("VU"),

        WALLIS_AND_FUTUNA("WF"),

        SAMOA("WS"),

        YEMEN("YE"),

        MAYOTTE("YT"),

        SOUTH_AFRICA("ZA"),

        ZAMBIA("ZM"),

        ZIMBABWE("ZW");

        private final String value;

        CountryEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static ISO3166.CountryEnum fromValue(String value) {
            for (ISO3166.CountryEnum b : ISO3166.CountryEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }

    }

}
